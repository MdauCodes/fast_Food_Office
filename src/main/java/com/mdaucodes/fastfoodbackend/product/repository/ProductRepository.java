package com.mdaucodes.fastfoodbackend.product.repository;

import com.mdaucodes.fastfoodbackend.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findProductByProductUuid(UUID productUuid);
    List<Product> findProductsByProductNameIgnoreCaseAndAndProductDescription(String searchString);
}
