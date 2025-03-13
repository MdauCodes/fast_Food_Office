package com.mdaucodes.fastfoodbackend.product.controller;

import com.mdaucodes.fastfoodbackend.product.dtos.DiscountAmount;
import com.mdaucodes.fastfoodbackend.product.dtos.ProductDTO;
import com.mdaucodes.fastfoodbackend.product.dtos.ProductModel;
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
    @PutMapping("/updateProductDiscountAmount/{adminEmail}/{productUuid}")
    public ResponseEntity<Product>  updateProductDiscountAmount(@PathVariable("adminEmail")String adminEmail,
                                                  @PathVariable("productUuid")UUID productUuid,
                                                  @RequestBody DiscountAmount discountAmount){
        return ResponseEntity.ok(productService.updateProductDiscountAmount(adminEmail, productUuid, discountAmount));
    }
    @DeleteMapping("/deleteProduct/{adminEmail}/{productUuid}")
    public ResponseEntity<String> deleteProduct(@PathVariable("adminEmail")String adminEmail,
                                                @PathVariable("productUuid")UUID productUuid){
        return ResponseEntity.ok(productService.deleteProduct(adminEmail, productUuid));
    }
}
