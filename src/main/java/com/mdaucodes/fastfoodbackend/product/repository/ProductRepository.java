package com.mdaucodes.fastfoodbackend.product.repository;

import com.mdaucodes.fastfoodbackend.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findProductByProductUuid(UUID productUuid);

    List<Product> findProductsByProductNameIgnoreCaseAndProductDescription(String name, String searchString);
    Optional<Product> findProductByProductNameIgnoreCaseAndAndProductDescriptionIgnoreCase(String name, String descr);

    List<Product> findProductsByProductNameIgnoreCaseAndProductDescriptionIgnoreCaseOrderByProductPriceAsc(String name, String searchString);
    List<Product> findProductsByProductNameIgnoreCaseAndProductDescriptionIgnoreCaseOrderByProductPriceDesc(String name, String searchString);

    @Query("SELECT p from Product p JOIN p.ingredientsList i WHERE i.ingredientName= :ingredientName")
    List<Product> fetchProductsContainingIngredient(@Param("ingredientName") String ingredientName);
}
