package com.satya.graphql.lec03.controller;

import com.satya.graphql.lec03.dto.Customer;
import com.satya.graphql.lec03.dto.CustomerOrder;
import com.satya.graphql.lec03.service.CustomerService;
import com.satya.graphql.lec03.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

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

  @SchemaMapping(typeName = "Customer")
  public Flux<CustomerOrder> orders(Customer customer, @Argument Integer limit) {
    System.out.println("orders method invoked for customer: " + customer.getName());
    return this.orderService.ordersByCustomerName(customer.getName()).take(limit);
  }
}
