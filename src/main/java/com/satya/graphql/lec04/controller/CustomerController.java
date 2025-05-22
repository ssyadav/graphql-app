package com.satya.graphql.lec04.controller;

import com.satya.graphql.lec04.dto.Customer;
import com.satya.graphql.lec04.dto.CustomerOrder;
import com.satya.graphql.lec04.service.CustomerService;
import com.satya.graphql.lec04.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

  @Autowired private CustomerService customerService;
  @Autowired private OrderService orderService;

//  @QueryMapping()
@SchemaMapping(typeName = "Query")
  public Flux<Customer> customers() {
    return this.customerService.customers();
  }

  /*@SchemaMapping(typeName = "Customer")
  public Flux<CustomerOrder> orders(Customer customer) {
    System.out.println("orders method invoked for customer: " + customer.getName());
    return this.orderService.ordersByCustomerName(customer.getName());
  }*/


  // N+1 problem
 /* @BatchMapping(typeName = "Customer")
  public Flux<List<CustomerOrder>> orders(List<Customer> list) {
    System.out.println("orders method invoked for customer: " + list);
    List<String> names = list.stream()
            .map(Customer::getName)
            .toList();
    return this.orderService.ordersByCustomerNames(names);
  }*/

  // Order missmatch issue - fix
  @BatchMapping(typeName = "Customer")
  public Mono<Map<Customer, List<CustomerOrder>>> orders(List<Customer> list) {
    System.out.println("orders method invoked for customer: " + list);
    return this.orderService.fetchOrdersAsMap(list);
  }
}
