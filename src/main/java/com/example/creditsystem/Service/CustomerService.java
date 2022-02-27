package com.example.creditsystem.Service;

import com.example.creditsystem.Model.Entity.Customer;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerService {

    Customer getCustomer(Integer id);

    void addCustomer(@RequestBody Customer customer);

    Customer updateCustomer(@RequestBody Customer customer);

    boolean deleteCustomer(Integer id);

    Customer getCustomerByIdentityNumber(String identityNumber);


}
