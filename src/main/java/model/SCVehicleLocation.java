package model;


public class SCVehicleLocation {
    private double latitude;
    private double longitude;
    private String created_on;

    public SCVehicleLocation() {
    }

    public SCVehicleLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
