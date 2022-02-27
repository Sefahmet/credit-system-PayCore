package com.example.creditsystem.Model;

import lombok.Data;

@Data
public class CustomerDto {

    private Integer id;
    private String identityNumber;
    private String firstName;
    private String lastName;
    private Integer monthlyIncome;
    private String phoneNumber;
}
