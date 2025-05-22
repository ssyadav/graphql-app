package com.satya.graphql.lec14.service;

import com.satya.graphql.lec14.dto.*;
import com.satya.graphql.lec14.repository.CustomerRepository;
import com.satya.graphql.lec14.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

  @Autowired private CustomerRepository customerRepository;
  @Autowired private CustomerEventService eventService;

  public Flux<CustomerDto> allCustomers() {
    return this.customerRepository
        .findAll()
        .map(EntityDtoUtil::toDto)
        .doOnError(e -> System.out.println("Error fetching customers: {}" + e.getMessage()));
  }

  public Mono<CustomerDto> customerById(Integer id) {
    return this.customerRepository
        .findById(id)
        .map(EntityDtoUtil::toDto)
        .doOnError(e -> System.out.println("Error fetching customers: {}" + e.getMessage()));
  }

  public Mono<CustomerDto> createCustomer(CustomerDto customerDto) {
    return Mono.just(customerDto)
        .map(EntityDtoUtil::toEntity)
        .flatMap(this.customerRepository::save)
        .map(EntityDtoUtil::toDto)
        .doOnNext(c -> this.eventService.emitEvent(CustomerEvent.create(c.getId(), Action.CREATED)));
  }

  public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto dto) {
    return this.customerRepository
        .findById(id)
        .map(c -> EntityDtoUtil.toEntity(id, dto))
        .flatMap(this.customerRepository::save)
        .map(EntityDtoUtil::toDto)
            .doOnNext(c -> this.eventService.emitEvent(CustomerEvent.create(c.getId(), Action.CREATED)));
  }

  public Mono<DeleteResponseDto> deleteCustomer(Integer id) {
    return this.customerRepository
        .deleteById(id)
         .doOnSuccess(c -> this.eventService.emitEvent(CustomerEvent.create(id, Action.DELETED)))
        .thenReturn(DeleteResponseDto.create(id, Status.SUCCESS))
        .onErrorReturn(DeleteResponseDto.create(id, Status.FAILURE));
  }
}
