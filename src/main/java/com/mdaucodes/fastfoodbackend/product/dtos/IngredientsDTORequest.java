package com.mdaucodes.fastfoodbackend.product.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IngredientsDTORequest {
    private String productUuid;
    private List<IngredientsDTO> ingredientsDTOList=new ArrayList<>();

    public IngredientsDTORequest() {
    }

    public IngredientsDTORequest(String productUuid, List<IngredientsDTO> ingredientsDTOList) {
        this.productUuid = productUuid;
        this.ingredientsDTOList = ingredientsDTOList;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(String productUuid) {
        this.productUuid = productUuid;
    }

    public List<IngredientsDTO> getIngredientsDTOList() {
        return ingredientsDTOList;
    }

    public void setIngredientsDTOList(List<IngredientsDTO> ingredientsDTOList) {
        this.ingredientsDTOList = ingredientsDTOList;
    }
}
