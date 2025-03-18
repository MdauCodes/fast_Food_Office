package com.mdaucodes.fastfoodbackend.product.entities;

import com.mdaucodes.fastfoodbackend.product.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;

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
    private String destinationDescription;
    private String destinationCity;
    private String destinationStreet;
    private Double destinationLatitude;
    private Double destinationLongitude;
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public CustomerOrder() {
    }

    public CustomerOrder(UUID orderUuid, String magicWord, UUID customerUuid, UUID productUuid,
                         Integer numberOfProducts, BigDecimal orderPrice, OrderStatus orderStatus,
                         String destinationDescription, String destinationCity, String destinationStreet,
                         Double destinationLatitude, Double destinationLongitude) {
        this.orderUuid = orderUuid;
        this.magicWord = magicWord;
        this.customerUuid = customerUuid;
        this.productUuid = productUuid;
        this.numberOfProducts = numberOfProducts;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.destinationDescription = destinationDescription;
        this.destinationCity = destinationCity;
        this.destinationStreet = destinationStreet;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
    }

    public CustomerOrder(Long orderId, UUID orderUuid, String magicWord, UUID customerUuid,
                         UUID productUuid, Integer numberOfProducts, BigDecimal orderPrice,
                         OrderStatus orderStatus, String destinationDescription,
                         String destinationCity, String destinationStreet, Double destinationLatitude,
                         Double destinationLongitude) {
        this.orderId = orderId;
        this.orderUuid = orderUuid;
        this.magicWord = magicWord;
        this.customerUuid = customerUuid;
        this.productUuid = productUuid;
        this.numberOfProducts = numberOfProducts;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.destinationDescription = destinationDescription;
        this.destinationCity = destinationCity;
        this.destinationStreet = destinationStreet;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
    }

    public CustomerOrder(Long orderId, UUID orderUuid, String magicWord, UUID customerUuid,
                         UUID productUuid, Integer numberOfProducts, BigDecimal orderPrice,
                         OrderStatus orderStatus, String destinationDescription, String destinationCity,
                         String destinationStreet, Double destinationLatitude, Double destinationLongitude,
                         List<Product> products) {
        this.orderId = orderId;
        this.orderUuid = orderUuid;
        this.magicWord = magicWord;
        this.customerUuid = customerUuid;
        this.productUuid = productUuid;
        this.numberOfProducts = numberOfProducts;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.destinationDescription = destinationDescription;
        this.destinationCity = destinationCity;
        this.destinationStreet = destinationStreet;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.products = products;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(UUID orderUuid) {
        this.orderUuid = orderUuid;
    }

    public String getMagicWord() {
        return magicWord;
    }

    public void setMagicWord(String magicWord) {
        this.magicWord = magicWord;
    }

    public UUID getCustomerUuid() {
        return customerUuid;
    }

    public void setCustomerUuid(UUID customerUuid) {
        this.customerUuid = customerUuid;
    }

    public UUID getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(UUID productUuid) {
        this.productUuid = productUuid;
    }

    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Integer numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDestinationDescription() {
        return destinationDescription;
    }

    public void setDestinationDescription(String destinationDescription) {
        this.destinationDescription = destinationDescription;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationStreet() {
        return destinationStreet;
    }

    public void setDestinationStreet(String destinationStreet) {
        this.destinationStreet = destinationStreet;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
