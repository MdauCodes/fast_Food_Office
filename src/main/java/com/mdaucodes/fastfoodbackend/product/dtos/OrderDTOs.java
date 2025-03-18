package com.mdaucodes.fastfoodbackend.product.dtos;

import java.util.UUID;

public class OrderDTOs {

    private UUID productUuid;
    private String magicWord;
    private String destinationDescription;
    private String destinationCity;
    private String destinationStreet;
    private Double destinationLatitude;
    private Double destinationLongitude;

    public OrderDTOs() {
    }

    public OrderDTOs(UUID productName, String magicWord, String destinationDescription,
                     String destinationCity, String destinationStreet, Double destinationLatitude,
                     Double destinationLongitude) {
        this.productUuid = productName;
        this.magicWord = magicWord;
        this.destinationDescription = destinationDescription;
        this.destinationCity = destinationCity;
        this.destinationStreet = destinationStreet;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
    }

    public UUID getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(UUID productUuid) {
        this.productUuid = productUuid;
    }

    public String getMagicWord() {
        return magicWord;
    }

    public void setMagicWord(String magicWord) {
        this.magicWord = magicWord;
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
