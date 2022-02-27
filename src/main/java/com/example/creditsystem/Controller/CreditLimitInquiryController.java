package com.example.creditsystem.Controller;

import com.example.creditsystem.Model.CustomerDto;
import com.example.creditsystem.Model.Entity.CreditLimit;
import com.example.creditsystem.Model.Mapper.CustomerMapper;
import com.example.creditsystem.Service.CreditLimitService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/credit-score-inquiry")
public class CreditLimitInquiryController {
    private final CreditLimitService creditLimitService;
    private static final CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);


    @GetMapping
    public ResponseEntity<String> welcome(){
        return new ResponseEntity("com.example.rest.response.welcome_credit_inquiry_system",HttpStatus.OK);
    }


    @PostMapping(value = "/get-credit-limit")
    public ResponseEntity<CreditLimit> createCreditInquiry(@Valid @RequestBody CustomerDto customer) {
        // Calculate Credit Limit
        CreditLimit creditLimit = creditLimitService.createCreditLimit(CUSTOMER_MAPPER.toEntity(customer));

        // credit limit should null because of credit score not found error
        // if credit limit value is 0(zero), the credit application returns rejected
        if(creditLimit == null){
            return new ResponseEntity("com.example.rest.error.credit_score_not_found",HttpStatus.NOT_FOUND);
        }
        else if (creditLimit.getCreditLimit().equals(0)){
            return new ResponseEntity("com.example.rest.response.credit_application_rejected",HttpStatus.NOT_ACCEPTABLE);
        }else {
            return new ResponseEntity(creditLimit,HttpStatus.OK);
        }
    }

    @GetMapping(value = "/get-credit-limit-tckn")
    public ResponseEntity<String> getCreditLimit(@Valid @RequestParam String identityNumber){

        // Get Credit Limit from DB
        CreditLimit creditLimit = creditLimitService.getCreditLimit(identityNumber);

        // if it is not exist returns null
        if (creditLimit !=null){
            return new ResponseEntity(creditLimit, HttpStatus.OK);

        }else {
            return new ResponseEntity("com.example.rest.error.credit_limit_not_found",HttpStatus.NOT_FOUND);
        }
    }
}

