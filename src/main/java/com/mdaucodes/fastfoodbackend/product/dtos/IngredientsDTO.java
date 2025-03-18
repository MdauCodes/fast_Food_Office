package com.mdaucodes.fastfoodbackend.product.dtos;

public class IngredientsDTO {
    private String ingredientName;
    private String ingredientQuantity;

    public IngredientsDTO() {
    }

    public IngredientsDTO(String ingredientName, String ingredientQuantity) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
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
}
