package com.satya.graphql.lec15.util;

import com.satya.graphql.lec15.dto.CustomerDto;
import com.satya.graphql.lec15.entity.Customer;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
    public static CustomerDto toDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        BeanUtils.copyProperties(customer, dto);
        return dto;
    }

    public static Customer toEntity(Integer id, CustomerDto dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        customer.setId(id);
        return customer;
    }

    public static Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }
}
