package com.mdaucodes.fastfoodbackend.product.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdaucodes.fastfoodbackend.product.entities.enums.FoodCategory;
import com.mdaucodes.fastfoodbackend.product.entities.enums.ProductStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private UUID productUuid;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private BigDecimal discountAmount;

    private Integer availableQuantity;
    private FoodCategory foodCategory;
    private LocalDate preparedOn;
    private Float productRating;
    private LocalDate createdOn;
    private ProductStatus productStatus;

    @ManyToMany(
            mappedBy = "products"
    )
    @JsonIgnore
    private List<CustomerOrder> customerOrderList = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "products"
    )
    @JsonIgnore
    private List<ProductIngredients> ingredientsList = new ArrayList<>();

    public Product() {
    }

    public Product(Long productId, UUID productUuid, String productName, String productDescription,
                   BigDecimal productPrice, BigDecimal discountAmount, Integer availableQuantity,
                   FoodCategory foodCategory, LocalDate preparedOn, Float productRating,
                   LocalDate createdOn, ProductStatus productStatus, List<CustomerOrder> customerOrderList,
                   List<ProductIngredients> ingredientsList) {
        this.productId = productId;
        this.productUuid = productUuid;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.discountAmount = discountAmount;
        this.availableQuantity = availableQuantity;
        this.foodCategory = foodCategory;
        this.preparedOn = preparedOn;
        this.productRating = productRating;
        this.createdOn = createdOn;
        this.productStatus = productStatus;
        this.customerOrderList = customerOrderList;
        this.ingredientsList = ingredientsList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountPrice) {
        this.discountAmount = discountPrice;
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

    public LocalDate getPreparedOn() {
        return preparedOn;
    }

    public void setPreparedOn(LocalDate preparedOn) {
        this.preparedOn = preparedOn;
    }

    public Float getProductRating() {
        return productRating;
    }

    public void setProductRating(Float productRating) {
        this.productRating = productRating;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList = customerOrderList;
    }

    public List<ProductIngredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<ProductIngredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
