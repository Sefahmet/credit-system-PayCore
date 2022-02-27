package com.example.creditsystem.Model.Mapper;

import com.example.creditsystem.Model.CreditLimitDto;
import com.example.creditsystem.Model.Entity.CreditLimit;
import org.mapstruct.Mapper;

@Mapper
public interface CreditLimitMapper {

    CreditLimitDto toDto(CreditLimit entity);
    CreditLimit toEntity(CreditLimitDto creditLimitDto);
}
