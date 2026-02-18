package com.okta.developer.store.web.graphql;

import com.okta.developer.store.domain.Customer;
import com.okta.developer.store.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerGraphQLController {

    private final CustomerRepository customerRepository;

    public CustomerGraphQLController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @QueryMapping
    public Flux<Customer> customers() {
        return customerRepository.findAll();
    }

    @QueryMapping
    public Mono<Customer> customer(@Argument Long id) {
        return customerRepository.findById(id);
    }
}
