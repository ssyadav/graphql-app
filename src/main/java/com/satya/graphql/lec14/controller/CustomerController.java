package com.satya.graphql.lec14.controller;

import com.satya.graphql.lec14.dto.CustomerDto;
import com.satya.graphql.lec14.dto.DeleteResponseDto;
import com.satya.graphql.lec14.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

  @Autowired private CustomerService customerService;

  @QueryMapping
  private Flux<CustomerDto> customers() {
    return this.customerService.allCustomers();
  }

  @QueryMapping
  private Mono<CustomerDto> customerById(@Argument Integer id) {
    return this.customerService.customerById(id);
  }

  @MutationMapping
  private Mono<CustomerDto> createCustomer(@Argument CustomerDto customer) {
    return this.customerService.createCustomer(customer);
  }

  @MutationMapping
  private Mono<CustomerDto> updateCustomer(
      @Argument Integer id, @Argument("customer") CustomerDto dto) {
    return this.customerService.updateCustomer(id, dto);
  }

  @MutationMapping
  private Mono<DeleteResponseDto> deleteCustomer(@Argument Integer id) {
    return this.customerService.deleteCustomer(id);
  }


/*
    GraphQL Queries and Mutations

    query GetAll {
      customers {
        id
                name
        age
                city
      }
    }

    query GetCustomerById {
      customerById(id : 2) {
        id
                name
        age
                city
      }
    }

    mutation CreateCustomer($customer: CustomerInput!) {
      createCustomer(customer: $customer) {
        id
                name
        age
                city

      }
    }


    mutation UpdateCustomer {
      updateCustomer(id: 1, customer: {
        name: "jcson"
      }) {
        id
                name
        age
                city

      }
    }

    mutation DeleteCustomer {
      deleteCustomer(id: 1) {
        id
                status
      }
    }

    mutation MultipleMutation($customer: CustomerInput!) {
      createCustomer(customer: $customer) {
        id
        name
        age
        city

      }
       updateCustomer(id: 6, customer: {
        name: "jcson"
      }) {
        id
        name
        age
        city

      }
    }
  */

 /*
  Variables:
   {
      "customer": {
      "name": "abie",
              "age": 45,
              "city": "detroit"
    }
    }
  */
}
