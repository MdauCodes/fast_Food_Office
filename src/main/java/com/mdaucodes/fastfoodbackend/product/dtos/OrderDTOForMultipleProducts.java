package com.mdaucodes.fastfoodbackend.product.dtos;

import java.util.List;
import java.util.UUID;

public class OrderDTOForMultipleProducts {
    private List<UUID> productUuids;
    private String magicWord;
    private String destinationDescription;
    private String destinationCity;
    private String destinationStreet;
    private Double destinationLatitude;
    private Double destinationLongitude;

    public OrderDTOForMultipleProducts() {
    }

    public OrderDTOForMultipleProducts(List<UUID> productUuids, String magicWord, String destinationDescription,
                                       String destinationCity, String destinationStreet,
                                       Double destinationLatitude, Double destinationLongitude) {
        this.productUuids = productUuids;
        this.magicWord = magicWord;
        this.destinationDescription = destinationDescription;
        this.destinationCity = destinationCity;
        this.destinationStreet = destinationStreet;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
    }

    public List<UUID> getProductUuids() {
        return productUuids;
    }

    public void setProductUuids(List<UUID> productUuids) {
        this.productUuids = productUuids;
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
