package com.mdaucodes.fastfoodbackend.product.dtos;

public class OrderLocationDTO {
    private String destinationDescription;
    private String destinationCity;
    private String destinationStreet;
    private Double destinationLatitude;
    private Double destinationLongitude;

    public OrderLocationDTO() {
    }

    public OrderLocationDTO(String destinationDescription, String destinationCity,
                            String destinationStreet, Double destinationLatitude, Double destinationLongitude) {
        this.destinationDescription = destinationDescription;
        this.destinationCity = destinationCity;
        this.destinationStreet = destinationStreet;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
    }

    public String getDestinationDescription() {
        return destinationDescription;
    }

    public void setDestinationDescription(String destinationDescription) {
        this.destinationDescription = destinationDescription;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationStreet() {
        return destinationStreet;
    }

    public void setDestinationStreet(String destinationStreet) {
        this.destinationStreet = destinationStreet;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }
}
