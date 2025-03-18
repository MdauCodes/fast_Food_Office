package com.mdaucodes.fastfoodbackend.product.service;

import com.mdaucodes.fastfoodbackend.product.dtos.*;
import com.mdaucodes.fastfoodbackend.product.entities.CustomerComplaints;
import com.mdaucodes.fastfoodbackend.product.entities.CustomerOrder;
import com.mdaucodes.fastfoodbackend.product.entities.ProductIngredients;
import com.mdaucodes.fastfoodbackend.product.entities.enums.FoodCategory;
import com.mdaucodes.fastfoodbackend.product.entities.Product;
import com.mdaucodes.fastfoodbackend.product.entities.enums.ProductStatus;
import com.mdaucodes.fastfoodbackend.product.repository.CustomerComplaintsRepository;
import com.mdaucodes.fastfoodbackend.product.repository.OrderRepository;
import com.mdaucodes.fastfoodbackend.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.mdaucodes.fastfoodbackend.product.entities.enums.ProductStatus.AVAILABLE;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerComplaintsRepository customerComplaintsRepository;

    public ProductService(ProductRepository productRepository, OrderRepository orderRepository,
                          CustomerComplaintsRepository customerComplaintsRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerComplaintsRepository = customerComplaintsRepository;
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
        product.setCreatedOn(LocalDate.now());
        product.setPreparedOn(LocalDate.now());

        productSetterMethod(productModel, product);
        if (productModel.getAvailableQuantity()!=null){
            product.setAvailableQuantity(productModel.getAvailableQuantity());
        }else{
            product.setAvailableQuantity(1);
        }
        if (productModel.getFoodCategory()!=null) {
            FoodCategory[] categories = FoodCategory.values();
            for (FoodCategory category : categories) {
                if (category.name().toLowerCase().contains(productModel.getFoodCategory().toLowerCase())) {
                    product.setFoodCategory(category);
                }
            }
        }
        if (productModel.getProductStatus()!=null) {
            ProductStatus[] productStatuses = ProductStatus.values();
            for (ProductStatus productStatus : productStatuses) {
                if (productStatus.name().toLowerCase().contains(productModel.getProductStatus().toLowerCase())) {
                    product.setProductStatus(productStatus);
                }
            }
        }
        if (product.getProductStatus()==null){
            product.setProductStatus(AVAILABLE );
        }
        /**
         * Lets now add the ingredients
         */
        if (!productModel.getIngredientsDTORequest().getIngredientsDTOList().isEmpty()){
//                product.setIngredientsList(productModel.getIngredientsDTORequest().getIngredientsDTOList().stream()
//                        .map(i-> new ProductIngredients(i.getIngredientName(), i.getIngredientQuantity()))
//                        .collect(Collectors.toList()));
            List<ProductIngredients> productIngredientsList= productModel.getIngredientsDTORequest()
                    .getIngredientsDTOList().stream().map(i->
                            new ProductIngredients(i.getIngredientName(),
                                    i.getIngredientQuantity())).collect(Collectors.toList());
            for (ProductIngredients productIngredient: productIngredientsList){
                productIngredient.setProducts(product);
            }
            product.setIngredientsList(productIngredientsList);
        }

        productRepository.save(product);
        return "Product was saved SUCCESSFULLY";
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


        productSetterMethod(productModel, product);
        if (productModel.getAvailableQuantity()!=null){
            product.setAvailableQuantity(productModel.getAvailableQuantity());
        }
        if (productModel.getFoodCategory()!=null){
            FoodCategory [] categories=FoodCategory.values();
            for (FoodCategory category: categories){
                if (category.name().toLowerCase().contains(productModel.getFoodCategory().toLowerCase())){
                    product.setFoodCategory(category);
                }
            }
        }
        if (productModel.getProductStatus()!=null) {
            ProductStatus[] productStatuses = ProductStatus.values();
            for (ProductStatus productStatus : productStatuses) {
                if (productStatus.name().toLowerCase().contains(productModel.getProductStatus().toLowerCase())) {
                    product.setProductStatus(productStatus);
                }
            }

        }

        /**
         * Below I'll be setting the ingredients info for a product
         */
       if (!productModel.getIngredientsDTORequest().getIngredientsDTOList().isEmpty()) {

           List<ProductIngredients> modelIngredientsList = productModel.getIngredientsDTORequest()
                   .getIngredientsDTOList().stream().map(ingredientsDTO -> new ProductIngredients(
                           ingredientsDTO.getIngredientName(), ingredientsDTO.getIngredientQuantity()))
                   .collect(Collectors.toList());

           if (product.getIngredientsList() == null || product.getIngredientsList().isEmpty()) {

               for (ProductIngredients productIngredients1: modelIngredientsList){

                   productIngredients1.setProducts(product);

               }
               product.setIngredientsList(modelIngredientsList);
           }else{
               List<ProductIngredients> existingIngredients=product.getIngredientsList();
                Iterator<ProductIngredients> ingredientsIterator= existingIngredients.iterator();

                while(ingredientsIterator.hasNext()){

                    ProductIngredients existingIngredient=ingredientsIterator.next();
                    for (ProductIngredients productIngredient: modelIngredientsList){
                        if (existingIngredient.getIngredientName().equalsIgnoreCase(productIngredient.getIngredientName()
                                .toLowerCase())){

                            existingIngredient.setIngredientQuantity(productIngredient.getIngredientQuantity());
                        }
//                        else{
//                            existingIngredients.add(productIngredient);
//                        }
                    }
                }
                List<ProductIngredients> newIngredientsList=new ArrayList<>();
                for (ProductIngredients modelIngredient: modelIngredientsList){
                    for (ProductIngredients existingIngredient: existingIngredients){
                        if (!modelIngredient.getIngredientName().equalsIgnoreCase(existingIngredient.getIngredientName())){
                            newIngredientsList.add(modelIngredient);
                        }
                    }
                }
               System.out.println("CHECK THIS OUT "+newIngredientsList.size());
                existingIngredients.addAll(newIngredientsList);

           }
       }

        return product;
    }

    private void productSetterMethod(ProductModel productModel, Product product) {
        if (productModel.getProductName()!=null && !productModel.getProductName().isBlank()){
            Optional<Product> product1=productRepository.findProductByProductNameIgnoreCaseAndAndProductDescriptionIgnoreCase(
                    productModel.getProductName(), productModel.getProductDescription());
            if (product1.isPresent()){
                throw new IllegalArgumentException("PRODUCT MUST BE UNIQUE IN TERMS OF NAME AND DESCRIPTION");
            }
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

    /**
     * Below is a logic for browsing and product listing
     * @return
     */
    public List<ProductDTO> fetchAllProductsForCustomer() {

        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(product.getProductUuid(), product.getProductName(),
                        product.getProductDescription(), product.getProductPrice(), product.getDiscountAmount(),
                        product.getIngredientsList().stream().map(productIngredients -> new IngredientsDTO(
                                productIngredients.getIngredientName(), productIngredients.getIngredientQuantity()))
                                .collect(Collectors.toList()),
                        product.getAvailableQuantity(), product.getFoodCategory(),
                        product.getProductRating(), product.getProductStatus()))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> fetchProductsByNameOrDescription(String searchString) {
        List<Product> productList=productRepository.findAll();
        List<ProductDTO> dtoList=new ArrayList<>();

        for (Product product: productList){
            if (product.getProductName().toLowerCase().contains(searchString.toLowerCase())
            || product.getProductDescription().toLowerCase().contains(searchString.toLowerCase())
            || searchString.toLowerCase().contains(product.getProductName().toLowerCase())
            || searchString.toLowerCase().contains(product.getProductDescription().toLowerCase())){

               dtoList.add(new ProductDTO(product.getProductUuid(), product.getProductName(), product.getProductDescription(),
                       product.getProductPrice(), product.getDiscountAmount(),
                       product.getIngredientsList().stream().map(i-> new IngredientsDTO(i.getIngredientName(),
                                       i.getIngredientQuantity())).collect(Collectors.toList()),
                       product.getAvailableQuantity(), product.getFoodCategory(), product.getProductRating(),
                       product.getProductStatus()));
            }
        }
        return dtoList;
    }

    public List<ProductDTO> fetchProductsByPriceAscending(String name) {
        //TODO:;:: Sorting the products

        List<Product> productList=productRepository
                .findProductsByProductNameIgnoreCaseAndProductDescriptionIgnoreCaseOrderByProductPriceAsc(name, name);
        return productList.stream()
                .map(product -> new ProductDTO(product.getProductUuid(), product.getProductName(),
                        product.getProductDescription(), product.getProductPrice(), product.getDiscountAmount(),
                        product.getIngredientsList().stream().map(productIngredients -> new IngredientsDTO(
                                productIngredients.getIngredientName(), productIngredients.getIngredientQuantity()))
                                .collect(Collectors.toList()),
                        product.getAvailableQuantity(), product.getFoodCategory(),
                        product.getProductRating(), product.getProductStatus()))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> fetchProductsByPriceDescending(String name) {
        //TODO:;:: Sorting the products

        List<Product> productList=productRepository
                .findProductsByProductNameIgnoreCaseAndProductDescriptionIgnoreCaseOrderByProductPriceDesc(name, name);
        return productList.stream()
                .map(product -> new ProductDTO(product.getProductUuid(), product.getProductName(),
                        product.getProductDescription(), product.getProductPrice(), product.getDiscountAmount(),
                        product.getIngredientsList().stream().map(productIngredients -> new IngredientsDTO(
                                productIngredients.getIngredientName(), productIngredients.getIngredientQuantity()))
                                .collect(Collectors.toList()),
                        product.getAvailableQuantity(), product.getFoodCategory(),
                        product.getProductRating(), product.getProductStatus()))
                .collect(Collectors.toList());
    }
    @Transactional
    public Object makeOrderContainingOneProduct(UUID customerUuid, UUID productUuid, OrderDTOs orderDTOs) {

        if (orderDTOs==null){
            throw new IllegalArgumentException("ORDER_MODEL CANNOT BE EMPTY/NULL");
        }
        if (orderDTOs.getProductUuid()==null){
            throw new IllegalArgumentException("ORDER_MODEL MUST CONTAIN A PRODUCT");
        }
        if (customerUuid==null){
            throw new IllegalArgumentException("CUSTOMER UUID CANNOT BE NULL");
        }

        verifyCustomerExists(customerUuid);
        Product product=verifyProductExistsByUuid(productUuid);


        CustomerOrder customerOrder=new CustomerOrder();
        customerOrder.setOrderUuid(UUID.randomUUID());
        if (orderDTOs.getProductUuid()!=null){
            customerOrder.setProducts(List.of(product));
        }
        if (orderDTOs.getDestinationCity()!=null || orderDTOs.getDestinationCity().isEmpty()){
            customerOrder.setDestinationCity(orderDTOs.getDestinationCity());
        }
        if (orderDTOs.getDestinationStreet()!=null || orderDTOs.getDestinationStreet().isEmpty()){
            customerOrder.setDestinationStreet(orderDTOs.getDestinationStreet());
        }
        if (orderDTOs.getDestinationDescription()!=null || orderDTOs.getDestinationDescription().isEmpty()){
            customerOrder.setDestinationDescription(orderDTOs.getDestinationDescription());
        }
        if (orderDTOs.getDestinationLatitude()!=null){
            customerOrder.setDestinationLatitude(orderDTOs.getDestinationLatitude());
        }
        if (orderDTOs.getDestinationLongitude()!=null){
            customerOrder.setDestinationLongitude(orderDTOs.getDestinationLongitude());
        }
        if (orderDTOs.getMagicWord()!=null ||orderDTOs.getMagicWord().isEmpty()){
            customerOrder.setMagicWord(orderDTOs.getMagicWord());
        }
        customerOrder.setOrderPrice(product.getProductPrice());
        customerOrder.setCustomerUuid(customerUuid);

        orderRepository.save(customerOrder);

        return orderRepository.findCustomerOrderByCustomerUuid(customerUuid).getOrderUuid();

    }
    @Transactional
    public Object makeOrderContainingMultipleProducts(UUID customerUuid, OrderDTOForMultipleProducts dto) {
        if (dto==null ){
            throw new IllegalArgumentException("ORDER_MODEL CANNOT BE EMPTY/NULL");
        }
        if (dto.getProductUuids()==null || dto.getProductUuids().isEmpty()){
            throw new IllegalArgumentException("ORDER_MODEL MUST CONTAIN A PRODUCT");
        }
        if (customerUuid==null){
            throw new IllegalArgumentException("CUSTOMER UUID CANNOT BE NULL");
        }
        verifyCustomerExists(customerUuid);

        int counter=1;
        CustomerOrder customerOrder=new CustomerOrder();
        customerOrder.setProductUuid(UUID.randomUUID());

        for (UUID productUuid: dto.getProductUuids()){

            Product product=productRepository.findProductByProductUuid(productUuid)
                    .orElseThrow(()->new IllegalArgumentException("PRODUCT::% NOT FOUND"));
            if (counter==1){
                customerOrder.setProducts(List.of(product));
                customerOrder.setOrderPrice(product.getProductPrice());

                //TODO:: Logic to add also the transport costs according to delivery distance or something similar
            }
            customerOrder.setOrderPrice(customerOrder.getOrderPrice().add(product.getProductPrice()));
            customerOrder.getProducts().add(product);

            counter++;
        }

        if (dto.getDestinationCity()!=null || dto.getDestinationCity().isEmpty()){
            customerOrder.setDestinationCity(dto.getDestinationCity());
        }
        if (dto.getDestinationStreet()!=null || dto.getDestinationStreet().isEmpty()){
            customerOrder.setDestinationStreet(dto.getDestinationStreet());
        }
        if (dto.getDestinationDescription()!=null || dto.getDestinationDescription().isEmpty()){
            customerOrder.setDestinationDescription(dto.getDestinationDescription());
        }
        if (dto.getDestinationLatitude()!=null){
            customerOrder.setDestinationLatitude(dto.getDestinationLatitude());
        }
        if (dto.getDestinationLongitude()!=null){
            customerOrder.setDestinationLongitude(dto.getDestinationLongitude());
        }
        if (dto.getMagicWord()!=null ||dto.getMagicWord().isEmpty()){
            customerOrder.setMagicWord(dto.getMagicWord());
        }

        customerOrder.setCustomerUuid(customerUuid);
        orderRepository.save(customerOrder);
        //TODO:: Logic to notify the admin/seller about the order made then acknowledging the buyer that their was received
        return orderRepository.findCustomerOrderByCustomerUuid(customerUuid);
    }

    @Transactional
    public String fileComplaint(UUID orderUuid, UUID productUuid, CustomerComplaintsDTO complaintsDTO) {

        CustomerOrder customerOrder=verifyIfOrderExistsByUuid(orderUuid);
        Product product=verifyProductExistsByUuid(productUuid);

        //TODO:: Logic for placement of complaints for a product that is within an order that contains many products

        CustomerComplaints customerComplaints=new CustomerComplaints();
        customerComplaints.setComplaint(complaintsDTO.getComplaint());
        customerComplaints.setCustomerUuid(customerOrder.getCustomerUuid());
        customerComplaints.setProductUuid(product.getProductUuid());

        customerComplaintsRepository.save(customerComplaints);
        //TODO:: Notify the admin or seller about a complaint
        return "COMPLAINT FOR PRODUCT named::"+product.getProductName()+" WAS SUCCESSFULLY RECEIVED";
    }

    public ResponseEntity<OrderLocationDTO> fetchOrderLocation(UUID orderUuid, String customerEmail) {

        CustomerOrder customerOrder=verifyIfOrderExistsByUuid(orderUuid);

        return ResponseEntity.ok(new OrderLocationDTO(customerOrder.getDestinationDescription(),
                customerOrder.getDestinationCity(), customerOrder.getDestinationStreet(),
                customerOrder.getDestinationLatitude(), customerOrder.getDestinationLongitude()));
    }
    //TODO: admin verification
    private void verifyCustomerExists(UUID customerUuid){

    }

    //TODO: admin verification
    private void verifyAdminEmail(String adminEmail){

    }

    private CustomerOrder verifyIfOrderExistsByUuid(UUID orderUuid){
        CustomerOrder customerOrder=orderRepository.getCustomerOrderByOrderUuid(orderUuid)
                .orElseThrow(()->new IllegalArgumentException("ORDER NOT FOUND"));
        return customerOrder;
    }
    private Product verifyProductExistsByUuid(UUID productUuid){
        Product product=productRepository.findProductByProductUuid(productUuid)
                .orElseThrow(()->new IllegalArgumentException("PRODUCT NOT FOUND"));
        return product;
    }


    public List<OrderDTOForMultipleProducts> fetchOrdersByCustomerUuid(UUID customerUuid) {
        //TODO:::: varify customer exists by the Uuid
        List<CustomerOrder> customerOrderList=orderRepository.getCustomerOrdersByCustomerUuid(customerUuid);
        if (customerOrderList.isEmpty()){
            throw new IllegalArgumentException("THE customer::");
        }

        return customerOrderList.stream().map(customerOrder -> new OrderDTOForMultipleProducts(
                customerOrder.getProducts().stream().map(Product::getProductUuid).collect(Collectors.toList()),
                customerOrder.getMagicWord(), customerOrder.getDestinationDescription(),
                customerOrder.getDestinationCity(), customerOrder.getDestinationStreet(),
                customerOrder.getDestinationLatitude(), customerOrder.getDestinationLongitude()
        )).collect(Collectors.toList());
    }


    @Transactional
    public IngredientsDTORequest deleteIngredientsFromAProduct(String productUuid) {
        UUID productUuid2=UUID.fromString(productUuid);
        Product product=verifyProductExistsByUuid(productUuid2);
        product.getIngredientsList().clear();

        return new IngredientsDTORequest(productUuid, product.getIngredientsList().stream().map(
                productIngredients -> new IngredientsDTO(productIngredients.getIngredientName(),
                        productIngredients.getIngredientQuantity())).collect(Collectors.toList()));
    }


    @Transactional
    public IngredientsDTORequest updateProductsIngredients(IngredientsDTORequest request) {
        UUID productUuid=UUID.fromString(request.getProductUuid());
        Product product=verifyProductExistsByUuid(productUuid);
        product.getIngredientsList().clear();

        List<ProductIngredients> productIngredientsList=new ArrayList<>();
        for(IngredientsDTO ingredientsDTO: request.getIngredientsDTOList()){
            productIngredientsList.add(new ProductIngredients(ingredientsDTO.getIngredientName(),
                    ingredientsDTO.getIngredientQuantity()));
        }
        product.getIngredientsList().addAll(productIngredientsList);

        return new IngredientsDTORequest(request.getProductUuid(), productIngredientsList.stream().map(i->
            new IngredientsDTO(i.getIngredientName(), i.getIngredientQuantity())).collect(Collectors.toList()));
    }

    public List<Product> adminFetchProductsContainingIngredient(String ingredientName) {
        List<Product> productList=productRepository.findAll();
        List<ProductIngredients> ingredientsList=new ArrayList<>();
        List<Product> list=new ArrayList<>();

        for (Product product: productList) {

            if (!product.getIngredientsList().isEmpty()){

                for (ProductIngredients ingredient: product.getIngredientsList()){

                    String ingredientName2= ingredient.getIngredientName();
                    if (ingredientName2.toLowerCase().contains(ingredientName.toLowerCase())
                    ||ingredientName.toLowerCase().contains(ingredientName2.toLowerCase())){

                        list.add(product);
                    }

                }
            }
        }
//        for (Product product: productList){
//                for (ProductIngredients productIngredients: product.getIngredientsList()){
//
//                    if (productIngredients.getIngredientName().toLowerCase().contains(ingredientName.toLowerCase())
//                            || ingredientName.toLowerCase().contains(productIngredients.getIngredientName())){
//
//                        list.add(new Product(product.getProductId(), product.getProductUuid(), product.getProductName(),
//                                product.getProductDescription(), product.getProductPrice(), product.getDiscountAmount(),
//                                product.getAvailableQuantity(), product.getFoodCategory(), product.getPreparedOn(),
//                                product.getProductRating(), product.getCreatedOn(), product.getProductStatus(),
//                                product.getCustomerOrderList(), product.getIngredientsList()));
//
//                        System.out.println("ArrayList SIZE= "+list.size());
//
//                    }
//            }
//
//        }

        return list;
    }

    public List<ProductDTO> fetchProductsContainingIngredient(String ingredientName) {
        List<Product> productList=productRepository.findAll();
        List<ProductDTO> productDTOList=new ArrayList<>();

        for (Product product: productList){
            for (ProductIngredients productIngredients: product.getIngredientsList()){
                if (productIngredients.getIngredientName().toLowerCase().contains(ingredientName.toLowerCase())
                || ingredientName.toLowerCase().contains(productIngredients.getIngredientName())){

                    productDTOList.add(new ProductDTO(product.getProductUuid(), product.getProductName(),
                            product.getProductDescription(), product.getProductPrice(), product.getDiscountAmount(),
                            product.getIngredientsList().stream().map(i->
                                    new IngredientsDTO(i.getIngredientName(),
                                    i.getIngredientQuantity())).collect(Collectors.toList()),
                            product.getAvailableQuantity(), product.getFoodCategory(),
                            product.getProductRating(), product.getProductStatus()));
                }
            }
        }
        return productDTOList;
    }
}
