package com.mdaucodes.fastfoodbackend.product.dtos;

import java.math.BigDecimal;


public class ProductModel {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private BigDecimal discountPrice;
    private Integer availableQuantity;
    private String foodCategory;
    private String productStatus;
    private IngredientsDTORequest ingredientsDTORequest;

    public ProductModel() {
    }

    public ProductModel(String productName, String productDescription, BigDecimal productPrice,
                        BigDecimal discountPrice, Integer availableQuantity,
                        String foodCategory, String productStatus) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        this.availableQuantity = availableQuantity;
        this.foodCategory = foodCategory;
        this.productStatus = productStatus;
    }

    public ProductModel(String productName, String productDescription, BigDecimal productPrice, BigDecimal discountPrice,
                        Integer availableQuantity, String foodCategory, String productStatus, IngredientsDTORequest request) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        this.availableQuantity = availableQuantity;
        this.foodCategory = foodCategory;
        this.productStatus = productStatus;
        this.ingredientsDTORequest = request;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public IngredientsDTORequest getIngredientsDTORequest() {
        return ingredientsDTORequest;
    }

    public void setIngredientsDTORequest(IngredientsDTORequest ingredientsDTORequest) {
        this.ingredientsDTORequest = ingredientsDTORequest;
    }
}
