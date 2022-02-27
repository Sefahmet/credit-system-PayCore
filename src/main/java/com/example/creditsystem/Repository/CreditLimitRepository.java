package com.example.creditsystem.Repository;

import com.example.creditsystem.Model.Entity.CreditLimit;
import com.example.creditsystem.Model.Entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;

public interface CreditLimitRepository extends JpaRepository<CreditLimit, Integer> {
    CreditLimit getCreditLimitByIdentityNumber(@Param("identity_number") String identityNumber);
}
