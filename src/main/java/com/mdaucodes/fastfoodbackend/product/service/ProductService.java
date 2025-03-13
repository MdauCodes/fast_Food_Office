package com.mdaucodes.fastfoodbackend.product.service;

import com.mdaucodes.fastfoodbackend.product.dtos.DiscountAmount;
import com.mdaucodes.fastfoodbackend.product.dtos.ProductDTO;
import com.mdaucodes.fastfoodbackend.product.dtos.ProductModel;
import com.mdaucodes.fastfoodbackend.product.entities.enums.FoodCategory;
import com.mdaucodes.fastfoodbackend.product.entities.Product;
import com.mdaucodes.fastfoodbackend.product.entities.enums.ProductStatus;
import com.mdaucodes.fastfoodbackend.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> fetchAllProducts(String adminEmail) {

        verifyAdminEmail(adminEmail);

        return productRepository.findAll();
    }

    @Transactional
    public String saveNewProduct(String adminEmail, ProductModel productModel) {

        if (productModel==null){
            throw new IllegalArgumentException("PRODUCT MODEL CANNOT BE EMPTY");
        }

        verifyAdminEmail(adminEmail);

        Product product=new Product();
        product.setProductUuid(UUID.randomUUID());
        if (productModel.getProductName()!=null && !productModel.getProductName().isBlank()){
            product.setProductName(productModel.getProductName());
        }
        if (productModel.getProductDescription()!=null && !productModel.getProductDescription().isBlank()){
            product.setProductDescription(productModel.getProductDescription());
        }
        if (productModel.getProductPrice()!=null){
            product.setProductPrice(productModel.getProductPrice());
        }
        if (productModel.getDiscountPrice()!=null ){
            product.setDiscountAmount(productModel.getDiscountPrice());
        }
        if (productModel.getAvailableQuantity()!=null){
            product.setAvailableQuantity(productModel.getAvailableQuantity());
        }else{
            product.setAvailableQuantity(1);
        }
        FoodCategory [] categories=FoodCategory.values();
        for (FoodCategory category: categories){
            if (category.name().toLowerCase().contains(productModel.getFoodCategory().name())){
                product.setFoodCategory(category);
            }
        }
        ProductStatus[] productStatuses=ProductStatus.values();
        for (ProductStatus productStatus: productStatuses){
            if (productStatus.name().toLowerCase().contains(productModel.getProductStatus().name())){
                product.setProductStatus(productStatus);
            }
        }
        if (product==null){
            return "Product Model was empty";
        }else{
            return "Product was saved SUCCESSFULLY";
        }
    }

    @Transactional
    public Product updateProduct(String adminEmail, UUID productUuid, ProductModel productModel) {

        if (productModel==null){
            throw new IllegalArgumentException("PRODUCT MODEL CANNOT BE EMPTY");
        }

        verifyAdminEmail(adminEmail);
//        verifyProductExistsByUuid(productUuid);

        Product product=productRepository.findProductByProductUuid(productUuid)
                .orElseThrow(()->new IllegalArgumentException("PRODUCT NOT FOUND BY UUID"));
        if (productModel.getProductName()!=null && !productModel.getProductName().isBlank()){
            product.setProductName(productModel.getProductName());
        }
        if (productModel.getProductDescription()!=null && !productModel.getProductDescription().isBlank()){
            product.setProductDescription(productModel.getProductDescription());
        }
        if (productModel.getProductPrice()!=null){
            product.setProductPrice(productModel.getProductPrice());
        }
        if (productModel.getDiscountPrice()!=null ){
            product.setDiscountAmount(productModel.getDiscountPrice());
        }
        if (productModel.getAvailableQuantity()!=null){
            product.setAvailableQuantity(productModel.getAvailableQuantity());
        }
        if (productModel.getFoodCategory()!=null){
            FoodCategory [] categories=FoodCategory.values();
            for (FoodCategory category: categories){
                if (category.name().toLowerCase().contains(productModel.getFoodCategory().name())){
                    product.setFoodCategory(category);
                }
            }
        }
        if (productModel.getProductStatus()!=null) {
            ProductStatus[] productStatuses = ProductStatus.values();
            for (ProductStatus productStatus : productStatuses) {
                if (productStatus.name().toLowerCase().contains(productModel.getProductStatus().name())) {
                    product.setProductStatus(productStatus);
                }
            }
        }

        return product;
    }

    public Product updateProductDiscountAmount(String adminEmail, UUID productUuid, DiscountAmount discountAmount) {
        if (discountAmount==null){
            throw new IllegalArgumentException("DISCOUNT AMOUNT CANNOT BE NULL");
        }
        verifyAdminEmail(adminEmail);

        Product product=productRepository.findProductByProductUuid(productUuid)
                .orElseThrow(()->new IllegalArgumentException("PRODUCT NOT FOUND BY UUID"));
        product.setDiscountAmount(discountAmount.getAmount());
        product.setProductPrice(product.getProductPrice().subtract(discountAmount.getAmount()));
        return product;
    }

    public String deleteProduct(String adminEmail, UUID productUuid) {
        String productName=" ";
        verifyAdminEmail(adminEmail);
        Product product=productRepository.findProductByProductUuid(productUuid)
                .orElseThrow(()->new IllegalArgumentException("PRODUCT NOT FOUND BY UUID"));
        productName=product.getProductName();
        productRepository.deleteById(product.getProductId());

        return "THE PRODUCT OF name::"+productName+" WAS DELETED SUCCESSFULLY";
    }

    //TODO: admin verification
    private void verifyAdminEmail(String adminEmail){

    }
    //TODO:: verify if product Exists By Uuid
    private void verifyProductExistsByUuid(UUID productUuid){

    }

    /**
     * Below is a logic for browsing and product listing
     * @return
     */
    public List<ProductDTO> fetchAllProductsForCustomer() {

        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(product.getProductUuid(), product.getProductName(),
                        product.getProductDescription(), product.getProductPrice(), product.getDiscountAmount(),
                        product.getIngredients(), product.getAvailableQuantity(), product.getFoodCategory(),
                        product.getProductRating(), product.getProductStatus()))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> fetchProductsByNameOrDescription(String searchString) {
        List<Product> productList=productRepository.findProductsByProductNameIgnoreCaseAndAndProductDescription(searchString);
        if (productList.isEmpty()){
            return productRepository.findAll().stream()
                    .map(product -> new ProductDTO(product.getProductUuid(), product.getProductName(),
                            product.getProductDescription(), product.getProductPrice(), product.getDiscountAmount(),
                            product.getIngredients(), product.getAvailableQuantity(), product.getFoodCategory(),
                            product.getProductRating(), product.getProductStatus()))
                    .collect(Collectors.toList());
        }
        else{
            return productList.stream()
                    .map(product -> new ProductDTO(product.getProductUuid(), product.getProductName(),
                            product.getProductDescription(), product.getProductPrice(), product.getDiscountAmount(),
                            product.getIngredients(), product.getAvailableQuantity(), product.getFoodCategory(),
                            product.getProductRating(), product.getProductStatus()))
                    .collect(Collectors.toList());
        }
    }

    public List<ProductDTO> fetchProductsByPriceAscending(BigDecimal lowestPrice) {
        //TODO:;:: Sorting the products
        return null;
    }

    public List<ProductDTO> fetchProductsByPriceDescending(BigDecimal highestPrice) {
        //TODO:;:: Sorting the products

        return null;
    }
}
