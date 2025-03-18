package com.mdaucodes.fastfoodbackend.product.dtos;

import com.mdaucodes.fastfoodbackend.product.entities.enums.FoodCategory;
import com.mdaucodes.fastfoodbackend.product.entities.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ProductDTO {
    private UUID productUuid;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private BigDecimal discountPrice;
    private List<IngredientsDTO> Ingredients=new ArrayList<>();
    private Integer availableQuantity;
    private FoodCategory foodCategory;
    private Float productRating;
    private ProductStatus productStatus;

    public ProductDTO() {
    }

    public ProductDTO(UUID productUuid, String productName, String productDescription, BigDecimal productPrice,
                      BigDecimal discountPrice, List<IngredientsDTO> ingredients, Integer availableQuantity, FoodCategory foodCategory,
                      Float productRating, ProductStatus productStatus) {
        this.productUuid = productUuid;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        Ingredients = ingredients;
        this.availableQuantity = availableQuantity;
        this.foodCategory = foodCategory;
        this.productRating = productRating;
        this.productStatus = productStatus;
    }

    public UUID getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(UUID productUuid) {
        this.productUuid = productUuid;
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

    public List<IngredientsDTO> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<IngredientsDTO> ingredients) {
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

    public Float getProductRating() {
        return productRating;
    }

    public void setProductRating(Float productRating) {
        this.productRating = productRating;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
