package com.example.creditsystem.Model.Mapper;

import com.example.creditsystem.Model.CustomerDto;
import com.example.creditsystem.Model.Entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto toDto(Customer entity);
    Customer toEntity(CustomerDto customerDto);
}
