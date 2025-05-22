package com.satya.graphql.lec02.controller;

import com.satya.graphql.lec02.dto.AgeRangeFilter;
import com.satya.graphql.lec02.dto.Customer;
import com.satya.graphql.lec02.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

  @Autowired private CustomerService customerService;

  @QueryMapping()
  public Flux<Customer> customers() {
    return this.customerService.customers();
  }

  @QueryMapping()
  public Mono<Customer> customerById(@Argument Integer id) {
    return this.customerService.customerById(id);
  }

  @QueryMapping()
  public Flux<Customer> customerNameContains(@Argument String name) {
    return this.customerService.nameContains(name);
  }

  @QueryMapping()
  public Flux<Customer> customerByAgeRange(@Argument Integer minAge, @Argument Integer maxAge) {
    return this.customerService.nameContains(minAge, maxAge);
  }

  @QueryMapping()
  public Flux<Customer> customerByAgeInput(@Argument AgeRangeFilter filter) {
    return this.customerService.nameContains(filter);
  }
}
