package com.okta.developer.product.web.graphql;

import com.okta.developer.product.domain.Product;
import com.okta.developer.product.repository.OrderItemRepository;
import com.okta.developer.product.repository.ProductCategoryRepository;
import com.okta.developer.product.repository.ProductOrderRepository;
import com.okta.developer.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.okta.developer.product.config.GraphQLConfiguration;
import org.springframework.context.annotation.Import;

@GraphQlTest(ProductGraphQLController.class)
@Import(GraphQLConfiguration.class)
class ProductGraphQLControllerIT {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductCategoryRepository productCategoryRepository;

    @MockBean
    private ProductOrderRepository productOrderRepository;

    @MockBean
    private OrderItemRepository orderItemRepository;

    @Test
    void shouldReturnAllProducts() {
        // Given
        Product product = new Product()
                .id(1L)
                .name("Test Product")
                .description("Description")
                .price(java.math.BigDecimal.TEN)
                .itemSize(com.okta.developer.product.domain.enumeration.Size.S);

        when(productRepository.findAll()).thenReturn(List.of(product));

        // When
        List<Product> products = graphQlTester.document("{ products { id name price } }")
                .execute()
                .path("products")
                .entityList(Product.class)
                .get();

        // Then
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Test Product");
    }
}
