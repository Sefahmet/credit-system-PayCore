package com.example.creditsystem.Repository;

import com.example.creditsystem.Model.Entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditScoreRepository extends JpaRepository<CreditScore,Integer> {

    CreditScore getCreditScoreByIdentityNumber(@Param("identity_number") String identityNumber);
}
