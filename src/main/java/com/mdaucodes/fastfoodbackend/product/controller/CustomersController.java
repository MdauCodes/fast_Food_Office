package com.mdaucodes.fastfoodbackend.product.controller;

import com.mdaucodes.fastfoodbackend.product.entities.CustomerComplaints;
import com.mdaucodes.fastfoodbackend.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mdaucodes/api/v1/product/")
public class CustomersController {
    private final ProductService productService;

    public CustomersController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Post new Order by Customer
     */
//    @PostMapping("/newCustomerOrder/")
//    public ResponseEntity<?> makeAnOrder(){
//
//    }
//    @PostMapping("/fileComplaint/{orderUuid}")
//    public ResponseEntity<String> fileComplaint(@PathVariable("orderUuid")UUID orderUuid,
//                                                @RequestBody CustomerComplaints complaints){
//
//        return ResponseEntity.ok(productService.fileComplaint(orderUuid, complaints));
//    }
//    @GetMapping("/fetchOrderLocation/{orderUuid}/{email}")
//    public ResponseEntity<?> fetchOrderLocation(@PathVariable("orderUuid")UUID orderUuid,
//                                                @PathVariable("email")String customerEmail){
//
//        return productService.fetchOrderLocation(orderUuid, customerEmail);
//    }
//    @PutMapping("/rateAndCommentProduct/{email}/{productUuid}")
//    public ResponseEntity<String> rateAndCommentProduct(@PathVariable("email")String customerEmail,
//                                                        @PathVariable("productUuid") UUID productUuid){
//
//        return ResponseEntity.ok(productService.rateAndCommentProduct(customerEmail, productUuid));
//
//    }
}
