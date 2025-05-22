package com.satya.graphql.lec11.controller;

import com.satya.graphql.lec11.dto.Book;
import com.satya.graphql.lec11.dto.Electronics;
import com.satya.graphql.lec11.dto.FruitDto;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class SearchController {
/*
  {
  search {
    type: __typename
    ... on Fruit {
      description
      expiryDate
    }
    ... on Electronics {
      description
      price
      brand
    }
    ... BookDetails
  }
}

fragment BookDetails on Book {
  author
  title
}
  */

  List<Object> list = List.of(
          FruitDto.create("banana",  LocalDate.now().plusDays(3)),
          FruitDto.create("apple",  LocalDate.now().plusDays(5)),
          Electronics.create("phone", 400, "SAMSUNG"),
          Electronics.create("mac book", 500, "APPLE"),
          Book.create("java",  "java book")
  );

 /* public Flux<Object> search() {
    return Mono.fromSupplier(() -> new ArrayList(list))
            .doOnNext(Collections::shuffle)
            .flatMapIterable(Function.identity())
            .take(ThreadLocalRandom.current().nextInt(1, list.size() + 1));
  }*/

  @QueryMapping
  public Flux<Object> search() {
    return Mono.fromSupplier(() -> new ArrayList(list))
            .map(l -> { Collections.shuffle(l); return l; })
            .flatMapIterable(l -> l)
            .take(ThreadLocalRandom.current().nextInt(1, list.size() + 1));
  }
}
