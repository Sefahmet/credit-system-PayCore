package com.example.creditsystem.Repository;


import com.example.creditsystem.Model.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer getCustomerByIdentityNumber(@Param("identity_number") String identityNumber);

}
