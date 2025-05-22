package com.satya.graphql.lec05.controller;

import com.satya.graphql.lec05.dto.Account;
import com.satya.graphql.lec05.dto.Customer;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class AccountController {

  /*@SchemaMapping
  public Mono<Account> account(Customer customer, DataFetchingFieldSelectionSet selectionSet) {
    System.out.println("account : " + selectionSet.getFields());
    String accountType = ThreadLocalRandom.current().nextBoolean() ? "CHECKING" : "SAVING";
    return Mono.just(
        Account.create(
            UUID.randomUUID(), ThreadLocalRandom.current().nextInt(1, 1000), accountType));
  }*/

  @SchemaMapping
  public Mono<Account> account(Customer customer, DataFetchingEnvironment environment) {
    System.out.println("Account : " + environment.getDocument());
    String accountType = ThreadLocalRandom.current().nextBoolean() ? "CHECKING" : "SAVING";
    return Mono.just(
        Account.create(
            UUID.randomUUID(), ThreadLocalRandom.current().nextInt(1, 1000), accountType));
  }
}
