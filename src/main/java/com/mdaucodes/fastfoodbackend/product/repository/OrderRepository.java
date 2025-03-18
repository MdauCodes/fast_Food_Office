package com.mdaucodes.fastfoodbackend.product.repository;

import com.mdaucodes.fastfoodbackend.product.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {

    CustomerOrder findCustomerOrderByCustomerUuid(UUID customerUuid);
    Optional<CustomerOrder> getCustomerOrderByOrderUuid(UUID orderUuid);

    List<CustomerOrder> getCustomerOrdersByCustomerUuid(UUID customerUuid);
}
