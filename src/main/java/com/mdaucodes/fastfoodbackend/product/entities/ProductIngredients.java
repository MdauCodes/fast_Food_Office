package com.mdaucodes.fastfoodbackend.product.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ProductIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId;
    private String ingredientName;
    private String ingredientQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product products ;

    public ProductIngredients() {
    }

    public ProductIngredients(String ingredientName, String ingredientQuantity) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
    }

    public ProductIngredients(String ingredientName, String ingredientQuantity, Product products) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
        this.products = products;
    }

    public ProductIngredients(Long ingredientId, String ingredientName, String ingredientQuantity) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
    }

    public ProductIngredients(Long ingredientId, String ingredientName, String ingredientQuantity,
                              Product products) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
        this.products = products;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(String ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

}
