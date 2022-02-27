package com.example.creditsystem.Service;


import com.example.creditsystem.Model.Entity.CreditScore;
import com.example.creditsystem.Model.Entity.Customer;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface CreditScoreService {


    CreditScore getCreditScore(Integer id);

    //CreditScore getCreditScoreByIdentityNumber(Integer identityNumber);

    CreditScore updateCreditScore(@RequestBody CreditScore creditScore);

    void addCreditScore(CreditScore creditScore);

    boolean deleteCreditScore(Integer id);
    CreditScore getCreditScoreByIdentityNumber(String identityNumber);



    //boolean deleteCreditScoreByIdentityNumber(Integer identityNumber);

}
