package com.mdaucodes.fastfoodbackend.product.dtos;

import com.mdaucodes.fastfoodbackend.product.entities.enums.FoodCategory;
import com.mdaucodes.fastfoodbackend.product.entities.enums.ProductStatus;

import java.math.BigDecimal;


public class ProductModel {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private BigDecimal discountPrice;
    private String Ingredients;
    private Integer availableQuantity;
    private FoodCategory foodCategory;
    private ProductStatus productStatus;

    public ProductModel() {
    }

    public ProductModel(String productName, String productDescription, BigDecimal productPrice,
                        BigDecimal discountPrice, String ingredients, Integer availableQuantity,
                        FoodCategory foodCategory, ProductStatus productStatus) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        Ingredients = ingredients;
        this.availableQuantity = availableQuantity;
        this.foodCategory = foodCategory;
        this.productStatus = productStatus;
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

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
