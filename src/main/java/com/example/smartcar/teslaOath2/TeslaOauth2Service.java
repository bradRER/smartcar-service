package com.example.smartcar.teslaOath2;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TeslaOauth2Service {

    public void initiate () {
        RestTemplate restTemplate = new RestTemplate();

        //todo fix anonymizeVin function (currently does nothing)
//        String url = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevin/" + "asdf" + "?format=json";
        String url = "https://auth.tesla.com/oauth2/v1/authorize";
//        String url = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevin/" + vin + "?format=json";

        Map uriVars = null;

        uriVars.put("client_id", "ownerapi");
        uriVars.put("code_challenge", "NDJjOWRmMWQ0OTU5NWVjYjczMWEwN2QzN2Y5YjU0Y2I1NDBkZTgyYzc0MDExZDIzNjVjZDcwMWZkNDc3MDQyOQ");
        uriVars.put("code_challenge_method", "S256");
        uriVars.put("redirect_uri", "https://auth.tesla.com/void/callback");
        uriVars.put("response_type", "code");
        uriVars.put("scope", "openid email offline_access");
        uriVars.put("state", "123abc");
//                client_id:ownerapi
//        code_challenge:NDJjOWRmMWQ0OTU5NWVjYjczMWEwN2QzN2Y5YjU0Y2I1NDBkZTgyYzc0MDExZDIzNjVjZDcwMWZkNDc3MDQyOQ
//        code_challenge_method:S256
//        redirect_uri:https://auth.tesla.com/void/callback
//        response_type:code
//        scope:openid email offline_access
//        state:123abc


        //request to api
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class, uriVars);

        String test = response.getBody();
    }

}
