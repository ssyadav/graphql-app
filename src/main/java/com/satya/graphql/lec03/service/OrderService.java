package com.satya.graphql.lec03.service;

import com.satya.graphql.lec03.dto.CustomerOrder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.*;

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
              CustomerOrder.create(UUID.randomUUID(), "satya-order7"),
              CustomerOrder.create(UUID.randomUUID(), "satya-order8"),
              CustomerOrder.create(UUID.randomUUID(), "satya-order9")));

  public Flux<CustomerOrder> ordersByCustomerName(String name) {
    return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
  }
}
