package com.satya.graphql.lec04.service;

import com.satya.graphql.lec04.dto.Customer;
import com.satya.graphql.lec04.dto.CustomerOrder;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Service
public class OrderService {
  private final Map<String, List<CustomerOrder>> map =
      Map.of(
          "Sam",
          List.of(
              CustomerOrder.create(UUID.randomUUID(), "sam-order1"),
              CustomerOrder.create(UUID.randomUUID(), "sam-order2"),
              CustomerOrder.create(UUID.randomUUID(), "sam-order3")),
          "mike",
          List.of(
              CustomerOrder.create(UUID.randomUUID(), "mike-order4"),
              CustomerOrder.create(UUID.randomUUID(), "mike-order5"),
              CustomerOrder.create(UUID.randomUUID(), "mike-order6")),
          "Jack",
          List.of(
              CustomerOrder.create(UUID.randomUUID(), "Jack-order7"),
              CustomerOrder.create(UUID.randomUUID(), "Jack-order8"),
              CustomerOrder.create(UUID.randomUUID(), "Jack-order9")));

  public Flux<CustomerOrder> ordersByCustomerName(String name) {
    return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
  }

 /* public Flux<List<CustomerOrder>> ordersByCustomerNames(List<String> names) {
    return Flux.fromIterable(names)
            .map(m -> map.getOrDefault(m, Collections.emptyList()));
  }*/

  // size miss match issue
  public Flux<List<CustomerOrder>> ordersByCustomerNamesSizeMissMatchIssue(List<String> names) {
    return Flux.fromIterable(names)
            .flatMap(m -> fetchOrders(m));
  }

  // size miss match issue -- fix but order mismatch issue
  public Flux<List<CustomerOrder>> ordersByCustomerNamesOrderMissMatchIssue(List<String> names) {
    return Flux.fromIterable(names)
            .flatMap(n -> fetchOrders(n).defaultIfEmpty(Collections.emptyList()));
  }

  // order mismatch issue -- fix
  public Flux<List<CustomerOrder>> ordersByCustomerNames(List<String> names) {
    return Flux.fromIterable(names)
            .flatMapSequential(n -> fetchOrders(n).defaultIfEmpty(Collections.emptyList()));
  }

  private Mono<List<CustomerOrder>> fetchOrders(String name) {
    return Mono.justOrEmpty(map.get(name))
        .delayElement(Duration.ofMillis(ThreadLocalRandom.current().nextInt(0,500)));
  }

  public Mono<Map<Customer, List<CustomerOrder>>> fetchOrdersAsMap(List<Customer> names) {
    return Flux.fromIterable(names)
            .map(c -> Tuples.of(c, map.getOrDefault(c.getName(), Collections.emptyList())))
            .collectMap(Tuple2::getT1, Tuple2::getT2);
  }
}
