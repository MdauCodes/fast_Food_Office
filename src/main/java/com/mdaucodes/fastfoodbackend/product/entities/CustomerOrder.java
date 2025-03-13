package com.mdaucodes.fastfoodbackend.product.entities;

import com.mdaucodes.fastfoodbackend.product.entities.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private UUID orderUuid;
    private String magicWord;
    private UUID customerUuid;
    private UUID productUuid;
    private Integer numberOfProducts;
    private BigDecimal orderPrice;
    private OrderStatus orderStatus;
    private String locationDescription;
}
