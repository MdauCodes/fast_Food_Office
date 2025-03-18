package com.mdaucodes.fastfoodbackend.product.controller;

import com.mdaucodes.fastfoodbackend.product.dtos.*;
import com.mdaucodes.fastfoodbackend.product.entities.Product;
import com.mdaucodes.fastfoodbackend.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mdaucodes/api/v1/product/admin/")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/fetchAllProducts/{adminEmail}")
    public List<Product> fetchAllProducts(@PathVariable("adminEmail") String adminEmail){
        return productService.fetchAllProducts(adminEmail);
    }
    @GetMapping("/fetchProductsByNameOrDescription/{searchString}")
    public List<ProductDTO> fetchProductsByNameOrDescription(@PathVariable("searchString") String searchString){
        return productService.fetchProductsByNameOrDescription(searchString);
    }
    @GetMapping("/adminFetchProductsContainingIngredient/{ingredientName}")
    public List<Product> adminFetchProductsContainingIngredient(@PathVariable("ingredientName") String ingredientName){
        return productService.adminFetchProductsContainingIngredient(ingredientName);
    }
    @PostMapping("/saveNewProduct/{adminEmail}")
    public ResponseEntity<String> saveNewProduct(@PathVariable("adminEmail")String adminEmail,
                                                 @RequestBody ProductModel productModel){
        return ResponseEntity.ok(productService.saveNewProduct(adminEmail, productModel));
    }
    @PutMapping("/updateProduct/{adminEmail}/{productUuid}")
    public ResponseEntity<Product>  updateProduct(@PathVariable("adminEmail")String adminEmail,
                                                  @PathVariable("productUuid")UUID productUuid,
                                                  @RequestBody ProductModel productModel){
        return ResponseEntity.ok(productService.updateProduct(adminEmail, productUuid, productModel));
    }
    @DeleteMapping("/deleteProduct/{adminEmail}/{productUuid}")
    public ResponseEntity<String> deleteProduct(@PathVariable("adminEmail")String adminEmail,
                                                @PathVariable("productUuid")UUID productUuid){
        return ResponseEntity.ok(productService.deleteProduct(adminEmail, productUuid));
    }



    /**
     * Below are the endpoints relating to Ingredients
     */

    @PostMapping("/updateProductsIngredients/")
    public ResponseEntity<IngredientsDTORequest>  updateProductsIngredients(@RequestBody IngredientsDTORequest request){
        return ResponseEntity.ok(productService.updateProductsIngredients(request));
    }
    @DeleteMapping("/deleteIngredientsFromAProduct/{productUuid}")
    public ResponseEntity<IngredientsDTORequest> deleteIngredientsFromProduct(
            @PathVariable ("productUuid")String productUuid){
        return ResponseEntity.ok(productService.deleteIngredientsFromAProduct(productUuid));
    }



    /**
     * Below are endpoints that relate to Discounts
     */
    @PutMapping("/updateProductDiscountAmount/{adminEmail}/{productUuid}")
    public ResponseEntity<Product>  updateProductDiscountAmount(@PathVariable("adminEmail")String adminEmail,
                                                                @PathVariable("productUuid")UUID productUuid,
                                                                @RequestBody DiscountAmount discountAmount){
        return ResponseEntity.ok(productService.updateProductDiscountAmount(adminEmail, productUuid, discountAmount));
    }
}
