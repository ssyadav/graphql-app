package com.satya.graphql.lec03.service;

import com.satya.graphql.lec03.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {

  private final Flux<Customer> customerFlux =
      Flux.just(
          Customer.create(1, "Sam", 30, "New York"),
          Customer.create(2, "Jack", 25, "Los Angeles"),
          Customer.create(3, "mike", 45, "miami"),
          Customer.create(4, "john", 40, "Chicago"));

  public Flux<Customer> customers() {
    return customerFlux;
  }

}
