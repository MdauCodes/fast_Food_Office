package com.mdaucodes.fastfoodbackend.product.controller;

import com.mdaucodes.fastfoodbackend.product.dtos.ProductDTO;
import com.mdaucodes.fastfoodbackend.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/mdaucodes/api/v1/product/customer/")
public class AllowedController {


    /**
     * Here are the endpoints to access the basic product listing and browsing functionality for a customer
     */

    private final ProductService productService;

    public AllowedController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/fetchAllProducts")
    public List<ProductDTO> fetchAllProducts(){

        return productService.fetchAllProductsForCustomer();
    }
    @GetMapping("/fetchProductsByNameOrDescription/{parameter}")
    public List<ProductDTO> fetchProductsByNameOrDescription(@PathVariable("parameter")String searchString){
        return productService.fetchProductsByNameOrDescription(searchString);
    }
    @GetMapping("/fetchProductsByPriceAscending/{price}")
    public List<ProductDTO> fetchProductsByPriceAscending(@PathVariable("price")BigDecimal lowestPrice){
        return productService.fetchProductsByPriceAscending(lowestPrice);
    }
    @GetMapping("/fetchProductsByPriceDescending/{price}")
    public List<ProductDTO> fetchProductsByPriceDescending(@PathVariable("price")BigDecimal highestPrice){
        return productService.fetchProductsByPriceDescending(highestPrice);
    }

}
