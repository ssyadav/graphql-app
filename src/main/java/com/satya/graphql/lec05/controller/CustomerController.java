package com.satya.graphql.lec05.controller;

import com.satya.graphql.lec05.dto.Customer;
import com.satya.graphql.lec05.service.CustomerService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

  @Autowired private CustomerService customerService;

  /*@QueryMapping()
  public Flux<Customer> customers(DataFetchingFieldSelectionSet selectionSet) {
    System.out.println("customers : " + selectionSet.getFields());
    return this.customerService.customers();
  }*/

  @QueryMapping()
  public Flux<Customer> customers(DataFetchingEnvironment environment) {
    System.out.println("Customers : " + environment.getDocument());
    System.out.println("Customers Operation Definition : " + environment.getOperationDefinition());
    return this.customerService.customers();
  }
}
