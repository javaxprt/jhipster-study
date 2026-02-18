package com.okta.developer.store.web.graphql;

import com.okta.developer.store.config.GraphQLConfiguration;
import com.okta.developer.store.domain.Customer;
import com.okta.developer.store.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@GraphQlTest(CustomerGraphQLController.class)
@Import(GraphQLConfiguration.class)
class CustomerGraphQLControllerIT {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void shouldReturnAllCustomers() {
        // Given
        Customer customer = new Customer()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .telephone("1234567890");

        when(customerRepository.findAll()).thenReturn(Flux.just(customer));

        // When
        List<Customer> customers = graphQlTester.document("{ customers { id firstName lastName } }")
                .execute()
                .path("customers")
                .entityList(Customer.class)
                .get();

        // Then
        assertThat(customers).hasSize(1);
        assertThat(customers.get(0).getFirstName()).isEqualTo("John");
        assertThat(customers.get(0).getLastName()).isEqualTo("Doe");
    }
}
