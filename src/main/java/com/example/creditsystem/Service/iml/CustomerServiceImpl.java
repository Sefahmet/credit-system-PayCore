package com.example.creditsystem.Service.iml;


import com.example.creditsystem.Exception.InvalidRequestException;
import com.example.creditsystem.Model.Entity.Customer;
import com.example.creditsystem.Repository.CustomerRepository;
import com.example.creditsystem.Exception.NotFoundException;
import com.example.creditsystem.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public Customer getCustomer(Integer id) throws NotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(()-> new NotFoundException("Customer"));
    }

    @Override
    public void addCustomer(Customer customer) throws InvalidRequestException {
        if(customer.getMonthlyIncome() > 0){
            customerRepository.save(customer);
        } else {
            throw new InvalidRequestException("Kullanıcı geliri 0 dan büyük olmalı");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        customerRepository.delete(getCustomer(id));
        return true;
    }

    @Override
    public Customer getCustomerByIdentityNumber(String identityNumber) {
        return customerRepository.getCustomerByIdentityNumber(identityNumber);
    }

}