package com.example.creditsystem.Service.iml;

import com.example.creditsystem.Model.Entity.CreditScore;
import com.example.creditsystem.Repository.CreditScoreRepository;
import com.example.creditsystem.Service.CreditScoreService;
import com.example.creditsystem.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CreditScoreServiceImpl implements CreditScoreService {

    Logger logger = LogManager.getLogger(CreditScoreServiceImpl.class);

    private final CreditScoreRepository creditScoreRepository;


    @Override
    public CreditScore getCreditScore(Integer id) {
        Optional<CreditScore> byId = creditScoreRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Credit Score"));
    }

    /*
        @Override
        public CreditScore getCreditScoreByIdentityNumber(Integer identityNumber) {
            return null;
        }
    */
    @Override
    public CreditScore updateCreditScore(CreditScore creditScore) {
        return creditScoreRepository.save(creditScore);
    }

    @Override
    public void addCreditScore(CreditScore creditScore) {
        creditScoreRepository.save(creditScore);

    }

    @Override
    public boolean deleteCreditScore(Integer id) {
        creditScoreRepository.delete(getCreditScore(id));
        return true;
    }

    @Override
    public CreditScore getCreditScoreByIdentityNumber(String identityNumber) {
        return creditScoreRepository.getCreditScoreByIdentityNumber(identityNumber);

    }

}
/*
    @Override
    public boolean deleteCreditScoreByIdentityNumber(Integer identityNumber) {
        return false;
    }
 */
