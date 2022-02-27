package com.example.creditsystem.Service;

import com.example.creditsystem.Model.Entity.CreditLimit;
import com.example.creditsystem.Model.Entity.Customer;

public interface CreditLimitService {

    CreditLimit createCreditLimit(Customer customer);

    CreditLimit getCreditLimit(String identityNumber);

    void addOrUpdateCustomer(Customer customer);

    void addCreditLimit(CreditLimit creditLimit);

    boolean updateCreditLimit(CreditLimit creditLimit);

}
