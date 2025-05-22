package com.satya.graphql.lec15.controller;

import com.satya.graphql.lec15.dto.CustomerDto;
import com.satya.graphql.lec15.dto.CustomerNotFound;
import com.satya.graphql.lec15.dto.DeleteResponseDto;
import com.satya.graphql.lec15.exceptions.ApplicationErrors;
import com.satya.graphql.lec15.service.CustomerService;
import graphql.schema.DataFetchingEnvironment;
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
  private Flux<CustomerDto> customers(DataFetchingEnvironment environment) {
    String callerId = environment.getGraphQlContext().get("caller-id");
    System.out.println("CALLER ID :: " + callerId);
    return this.customerService.allCustomers();
  }

  @QueryMapping
  private Mono<Object> customerById(@Argument Integer id) {
    /*return this.customerService.customerById(id)
            .switchIfEmpty(ApplicationErrors.noSuchUser(id));*/
    return this.customerService.customerById(id)
            .cast(Object.class)
            .switchIfEmpty(Mono.just(CustomerNotFound.create(id)));
  }

  @MutationMapping
  private Mono<CustomerDto> createCustomer(@Argument CustomerDto customer) {
    return Mono.just(customer)
            .filter(c -> c.getAge() >= 18)
            .flatMap(this.customerService::createCustomer)
            .switchIfEmpty(ApplicationErrors.mustBe18(customer));
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

/*
  query GetAll {
    customers {
      id
              name
      age
              city
    }
    customerById(id : 2) {
      id
              name
      age
              city
    }
  }

  query GetCustomerById {
    a:customerById(id : 2) {
      id
              name
      age
              city
    }
    b:customerById(id : 2) {
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
  }*/
}
