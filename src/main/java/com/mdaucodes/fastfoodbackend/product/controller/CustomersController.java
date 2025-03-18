package com.mdaucodes.fastfoodbackend.product.controller;

import com.mdaucodes.fastfoodbackend.product.dtos.CustomerComplaintsDTO;
import com.mdaucodes.fastfoodbackend.product.dtos.OrderDTOForMultipleProducts;
import com.mdaucodes.fastfoodbackend.product.dtos.OrderDTOs;
import com.mdaucodes.fastfoodbackend.product.dtos.OrderLocationDTO;
import com.mdaucodes.fastfoodbackend.product.entities.CustomerComplaints;
import com.mdaucodes.fastfoodbackend.product.entities.CustomerOrder;
import com.mdaucodes.fastfoodbackend.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mdaucodes/api/v1/product/")
public class CustomersController {
    private final ProductService productService;

    public CustomersController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Post new Order by Customer::Single Product:
     */
    @PostMapping("/makeOrderContainingOneProduct/{customerUuid}/{productUuid}")
    public ResponseEntity<?> makeOrderContainingOneProduct(@PathVariable("customerUuid")UUID customerUuid,
                                         @PathVariable("productUuid")UUID productUuid,
                                         @RequestBody OrderDTOs orderDTOs){

        return ResponseEntity.ok(productService.makeOrderContainingOneProduct(customerUuid, productUuid, orderDTOs));
    }
    @PostMapping("/makeOrderContainingMultipleProducts/{customerUuid}")
    public ResponseEntity<?> makeOrderContainingMultipleProducts(@PathVariable("customerUuid")UUID customerUuid,
                                         @RequestBody OrderDTOForMultipleProducts dto){

        return ResponseEntity.ok(productService.makeOrderContainingMultipleProducts(customerUuid, dto));
    }
    @PostMapping("/fileComplaint/{orderUuid}")
    public ResponseEntity<String> fileComplaint(@PathVariable("orderUuid")UUID orderUuid,
                                                @RequestParam UUID productUuid,
                                                @RequestBody CustomerComplaintsDTO complaintsDTO){

        return ResponseEntity.ok(productService.fileComplaint(orderUuid, productUuid, complaintsDTO));
    }

    @GetMapping("/fetchOrdersByCustomerUuid/{customerUuid}")
    public List<OrderDTOForMultipleProducts> fetchOrdersByCustomerUuid(@PathVariable UUID customerUuid){
        return productService.fetchOrdersByCustomerUuid(customerUuid);
    }
    @GetMapping("/fetchOrderLocation/{orderUuid}/{email}")
    public ResponseEntity<OrderLocationDTO> fetchOrderLocation(@PathVariable("orderUuid")UUID orderUuid,
                                                               @PathVariable("email")String customerEmail){

        return productService.fetchOrderLocation(orderUuid, customerEmail);
    }

//    @PutMapping("/rateAndCommentProduct/{email}/{productUuid}")
//    public ResponseEntity<String> rateAndCommentProduct(@PathVariable("email")String customerEmail,
//                                                        @PathVariable("productUuid") UUID productUuid){
//
//        return ResponseEntity.ok(productService.rateAndCommentProduct(customerEmail, productUuid));
//    }

}
