package com.example.creditsystem.Service.iml;

import com.example.creditsystem.Exception.InvalidRequestException;
import com.example.creditsystem.Model.Entity.CreditLimit;
import com.example.creditsystem.Model.Entity.Customer;
import com.example.creditsystem.Repository.CreditLimitRepository;
import com.example.creditsystem.Repository.CreditScoreRepository;
import com.example.creditsystem.Service.CreditLimitService;
import com.example.creditsystem.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CreditLimitServiceImpl implements CreditLimitService {


    private final CustomerService customerService;
    private final CreditLimitRepository creditLimitRepository;
    private final CreditScoreRepository creditScoreRepository;

    @Override
    public CreditLimit createCreditLimit(Customer customer) {

        Integer creditScore;
        try {
            creditScore = creditScoreRepository.getCreditScoreByIdentityNumber(customer.getIdentityNumber()).getCreditScore();
        } catch (Exception e) {
            return  null;
        }

        CreditLimit personalCreditLimit = new CreditLimit();
        personalCreditLimit.setIdentityNumber(customer.getIdentityNumber());

        addOrUpdateCustomer(customer);
        // add used for recording Customer to DB
        // Kredi Skoru 500'ün altındaysa kredi limiti 0 olarak atanır.

        if (creditScore < 500) {

            personalCreditLimit.setCreditLimit(0);
            addCreditLimit(personalCreditLimit);

            return personalCreditLimit;

        }

        // Kredi Skoru 500-1000 arasında ve aylık geliri 5000 TL'nin altında.
        else if (creditScore < 1000 && customer.getMonthlyIncome() < 5000) {

            personalCreditLimit.setCreditLimit(10000);
            addCreditLimit(personalCreditLimit);

            return personalCreditLimit;

        }
        // Kredi Skoru 500-1000 arasında ve aylık geliri 5000 TL'nin üzerinde.
        else if (creditScore < 1000 && customer.getMonthlyIncome() >= 5000) {

            personalCreditLimit.setCreditLimit(20000);
            addCreditLimit(personalCreditLimit);

            return personalCreditLimit;

        }
        // Kredi Skoru 1000 veya daha büyük.
        else {

            Integer creditLimitValue = customer.getMonthlyIncome() * 4;
            personalCreditLimit.setCreditLimit(creditLimitValue);
            addCreditLimit(personalCreditLimit);

            return personalCreditLimit;

        }
    }

    @Override
    public CreditLimit getCreditLimit(String identityNumber) {

        CreditLimit finalCreditLimit = creditLimitRepository.getCreditLimitByIdentityNumber(identityNumber);

        if(finalCreditLimit != null) {

            return finalCreditLimit;

        }else{

            return null;

        }

    }

    @Override
    public void addOrUpdateCustomer(Customer customer) {

        try {

            Integer id = customerService.getCustomerByIdentityNumber(customer.getIdentityNumber()).getId();
            customer.setId(id);
            customerService.updateCustomer(customer);

        } catch(Exception e) {

            customerService.addCustomer(customer);

        }
    }

    @Override
    public void addCreditLimit(CreditLimit creditLimit) {

        if(creditLimitRepository.getCreditLimitByIdentityNumber(creditLimit.getIdentityNumber() ) == null){

            creditLimitRepository.save(creditLimit);

        }else{

            Integer id = creditLimitRepository.getCreditLimitByIdentityNumber(creditLimit.getIdentityNumber()).getId();
            creditLimit.setId(id);
            creditLimitRepository.save(creditLimit);

        }
    }

    @Override
    public boolean updateCreditLimit(CreditLimit creditLimit) {
        if (creditLimit.getId() == null) {

            throw new InvalidRequestException("Passenger id can not be null for update!");

        }
        try{

            creditLimitRepository.save(creditLimit);
            return true;

        }catch (Exception e){

            return false;

        }
    }
}
