package com.mdaucodes.fastfoodbackend.product.repository;

import com.mdaucodes.fastfoodbackend.product.entities.CustomerComplaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerComplaintsRepository extends JpaRepository<CustomerComplaints, Long> {
}
