package com.satya.graphql.lec02.service;

import com.satya.graphql.lec02.dto.AgeRangeFilter;
import com.satya.graphql.lec02.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

  public Mono<Customer> customerById(Integer id) {
    return customerFlux.filter(c -> c.getId().equals(id)).next();
  }

  public Flux<Customer> nameContains(String name) {
    return customerFlux.filter(c -> c.getName().contains(name));
  }

  public Flux<Customer> nameContains(Integer minAge, Integer maxAge) {
    return customerFlux.filter(c -> c.getAge() >= minAge && c.getAge() <= maxAge);
  }

  public Flux<Customer> nameContains(AgeRangeFilter filter) {
    return customerFlux.filter(c -> c.getAge() >= filter.getMinAge() && c.getAge() <= filter.getMaxAge());
  }
}
