package com.satya.graphql.lec10.controller;

import com.satya.graphql.lec10.dto.Book;
import com.satya.graphql.lec10.dto.Electronics;
import com.satya.graphql.lec10.dto.FruitDto;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Controller
public class ProductController {
/*
  {
    products {
    id
            description
    price
            __typename
    ... on Fruit {
      expiryDate
    }
    ... on Electronics {
      brand
    }
    ... on Book {
      author
    }
  }
  }
  */
  @QueryMapping
  public Flux<Object> products() {
    return Flux.just(
        FruitDto.create("banana", 1, LocalDate.now().plusDays(3)),
        FruitDto.create("apple", 2, LocalDate.now().plusDays(5)),
        Electronics.create("phone", 400, "SAMSUNG"),
        Electronics.create("mac book", 500, "APPLE"),
        Book.create("java", 300, "java book"));
  }
}
