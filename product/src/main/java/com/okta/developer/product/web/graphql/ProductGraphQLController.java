package com.okta.developer.product.web.graphql;

import com.okta.developer.product.domain.Product;
import com.okta.developer.product.domain.ProductCategory;
import com.okta.developer.product.domain.ProductOrder;
import com.okta.developer.product.domain.OrderItem;
import com.okta.developer.product.repository.ProductRepository;
import com.okta.developer.product.repository.ProductCategoryRepository;
import com.okta.developer.product.repository.ProductOrderRepository;
import com.okta.developer.product.repository.OrderItemRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductGraphQLController {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductOrderRepository productOrderRepository;
    private final OrderItemRepository orderItemRepository;

    public ProductGraphQLController(
            ProductRepository productRepository,
            ProductCategoryRepository productCategoryRepository,
            ProductOrderRepository productOrderRepository,
            OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productOrderRepository = productOrderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @QueryMapping
    public List<Product> products() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Optional<Product> product(@Argument Long id) {
        return productRepository.findById(id);
    }

    @QueryMapping
    public List<ProductCategory> productCategories() {
        return productCategoryRepository.findAll();
    }

    @QueryMapping
    public Optional<ProductCategory> productCategory(@Argument Long id) {
        return productCategoryRepository.findById(id);
    }

    @QueryMapping
    public List<ProductOrder> productOrders() {
        return productOrderRepository.findAll();
    }

    @QueryMapping
    public Optional<ProductOrder> productOrder(@Argument Long id) {
        return productOrderRepository.findById(id);
    }

    @QueryMapping
    public List<OrderItem> orderItems() {
        return orderItemRepository.findAll();
    }

    @QueryMapping
    public Optional<OrderItem> orderItem(@Argument Long id) {
        return orderItemRepository.findById(id);
    }
}
