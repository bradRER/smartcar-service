package com.example.smartcar.vehicleInfoAddl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class VehicleInfoAddlService {

    private final VehicleInfoAddlRepository vehicleInfoAddlRepository;
    @Autowired
    public VehicleInfoAddlService(VehicleInfoAddlRepository repository) {
        this.vehicleInfoAddlRepository = repository;
    }



    public Optional<VehicleInfoAddl> getVehicleInfoAddlById(Integer id) {
        return vehicleInfoAddlRepository.findById(id);
    }

    public Optional<VehicleInfoAddl> getVehicleInfoAddlByVinA(String vin) {
        return vehicleInfoAddlRepository.findByVinA(vin);
    }

    public VehicleInfoAddl updateVehicleInfoAddl(VehicleInfoAddl vehicleInfoAddl) {
        VehicleInfoAddl updated = vehicleInfoAddlRepository.update(vehicleInfoAddl);

        return updated;
    }

    public String deleteById(Integer id) {
        Optional<VehicleInfoAddl> vehicleInfoAddl = vehicleInfoAddlRepository.findById(id);
        if (vehicleInfoAddl.isPresent() ) {
            vehicleInfoAddlRepository.delete(vehicleInfoAddl.get());
            return "Ok";
        } else return "Error";

//        repository.deleteById(id);
    }

    public VehicleInfoAddl create(String vin) throws JsonProcessingException {
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl();
        RestTemplate restTemplate = new RestTemplate();

        //todo fix anonymizeVin function (currently does nothing)
//        String url = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevin/" + "asdf" + "?format=json";
        String url = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevin/" + anonymizeVin(vin) + "?format=json";
//        String url = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevin/" + vin + "?format=json";

            //request to api
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String body = response.getBody();
            JsonNode arrNode = new ObjectMapper().readTree(body).get("Results");

            //set required obj vars
            //todo revise upon chassis hookup
            vehicleInfoAddl.setVinA(vin);
            Date date = new Date(System.currentTimeMillis());
            vehicleInfoAddl.setCreatedAt(date);
            vehicleInfoAddl.setCreatedBy(7);
            vehicleInfoAddl.setTelematicsRequestId(25);
            vehicleInfoAddl.setTelematicsRequestServerId("A");

            // if vin is invalid make, model, year should be null
            parse(arrNode, vehicleInfoAddl);

            // entry record should have year, make, model.
            if (vehicleInfoAddl.getMakeA() != null &&
                    vehicleInfoAddl.getModelA() != null &&
                    vehicleInfoAddl.getYearA() != null)  {
                vehicleInfoAddlRepository.save(vehicleInfoAddl);
            }

        return vehicleInfoAddl;
    }


    private void parse(JsonNode arrNode, VehicleInfoAddl vehicleInfoAddl) {
        Iterator<JsonNode> IterNode = arrNode.elements();

        while (IterNode.hasNext()) {
//              each node has this format
//                {
//                        "Value": "Class 2E: 6,001 - 7,000 lb (2,722 - 3,175 kg)",
//                        "ValueId": "14",
//                        "Variable": "Gross Vehicle Weight Rating From",
//                        "VariableId": 25
//                }

            JsonNode node = IterNode.next();
            String switcher = node.get("Variable").asText();
            String nuller = ""; // nuller keeps fields null instead of "null"
            switch ( switcher) {
                case "Make":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setMakeA(node.get("Value").asText());}
                    break;
                case "Manufacturer Name":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setMfgName(node.get("Value").asText());}
                    break;
                case "Model":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setModelA(node.get("Value").asText());}
                    break;
                case "Model Year":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setYearA(Integer.parseInt(node.get("Value").asText()));}
                    break;
                case "Series":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setSeries(node.get("Value").asText());}
                    break;
                case "Trim":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setTrim(node.get("Value").asText());}
                    break;
                case "Vehicle Type":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setVehicleType(node.get("Value").asText());}
                    break;
                case "Body Class":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setBodyClass(node.get("Value").asText());}
                    break;
                case "Doors":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setDoors(node.get("Value").asInt());}
                    break;
                case "Wheel Base (inches) From":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setWheelBase(Float.parseFloat(node.get("Value").asText()));}
                    break;
                case "Steering Location":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setSteeringLocation(node.get("Value").asText());}
                    break;
                case "Number of Seats":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setNbrOfSeats(Integer.parseInt(node.get("Value").asText()));}
                    break;
                case "Number of Seat Rows":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setNbrOfSeatRows(Integer.parseInt(node.get("Value").asText()));}
                    break;
                case "Transmission Style":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setTransmissionStyle(node.get("Value").asText());}
                    break;
                case "Transmission Speeds":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setTransmissionSpeeds(Integer.parseInt(node.get("Value").asText())); }
                    break;
                case "Axles":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setAxles(Integer.parseInt( node.get("Value").asText()));}
                    break;
                case "Fuel Type - Primary":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setFuelTypePrimary(node.get("Value").asText());}
                    break;
                case "Electrification Level":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setElectrificationLevel(node.get("Value").asText());}
                    break;
                case "Other Engine Info":
                    nuller = node.get("Value").asText();
                    if (nuller != "null") { vehicleInfoAddl.setOtherEngineInfo(node.get("Value").asText());}
                    break;
                default:
                    break;
            }
        }

    }


    private String anonymizeVin(String vin) {
//        return "asdf";
        return vin;
//        return vin.substring(0,8) + "*" + vin.substring(vin.length()-2);
    }


}
