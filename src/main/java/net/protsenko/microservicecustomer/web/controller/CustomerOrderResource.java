package net.protsenko.microservicecustomer.web.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.protsenko.microservicecustomer.domain.Customer;
import net.protsenko.microservicecustomer.domain.Order;
import net.protsenko.microservicecustomer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CustomerOrderResource {

    private static final String ENTITY_NAME = "order";

    @Value("${spring.application.name}")
    private String applicationName;

    private final CustomerRepository customerRepository;

    public CustomerOrderResource(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customerOrders/{customerId}")
    public ResponseEntity<Order> createOrder(@PathVariable String customerId, @Valid @RequestBody Order order) {
        log.debug("REST request to save Order : {} for Customer ID: {}", order, customerId);
        if (customerId.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Customer: " + ENTITY_NAME);
        }
        final Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            final var customer = customerOptional.get();
            customer.addOrder(order);
            customerRepository.save(customer);
            return ResponseEntity.ok()
                    .body(order);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer: " + ENTITY_NAME);
        }
    }
}
