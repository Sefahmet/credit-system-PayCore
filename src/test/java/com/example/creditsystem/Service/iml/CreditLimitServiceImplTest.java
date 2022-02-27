package com.example.creditsystem.Service.iml;

import com.example.creditsystem.Model.Entity.CreditLimit;
import com.example.creditsystem.Model.Entity.CreditScore;
import com.example.creditsystem.Model.Entity.Customer;
import com.example.creditsystem.Model.Mapper.CreditLimitMapper;
import com.example.creditsystem.Repository.CreditLimitRepository;
import com.example.creditsystem.Repository.CreditScoreRepository;
import com.example.creditsystem.Service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreditLimitServiceImplTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private CreditScoreRepository creditScoreRepository;

    @Mock
    private CreditLimitRepository creditLimitRepository;

    @InjectMocks
    private CreditLimitServiceImpl creditLimitService;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
    }
    @Test
    void createCreditLimit_successful() {
        // init step
        CreditLimit expectedCreditLimit = new CreditLimit(null, "77777777777", 16000);

        Customer expectedCustomer = new Customer(7,"77777777777","Sefa","Altundal",4000,"5324992217");
        // stub - when step
        Optional<CreditLimit> expectedOptPassenger = Optional.of(expectedCreditLimit);
        when(creditScoreRepository.getCreditScoreByIdentityNumber(expectedCustomer.getIdentityNumber())).thenReturn(new CreditScore(1,"77777777777",1000));

        // then step
        CreditLimit actualCreditLimit = creditLimitService.createCreditLimit(expectedCustomer);

        // valid step
        assertEquals(expectedCreditLimit, actualCreditLimit);
    }

    @Test
    void createCreditLimit_creditScoreNotFound() {
        // init step
        Customer expectedCustomer = new Customer(7,"77777777777","Sefa","Altundal",4000,"5324992217");

        // stub - when step
        when(creditScoreRepository.getCreditScoreByIdentityNumber(expectedCustomer.getIdentityNumber())).thenReturn(null);

        // then step
        CreditLimit actualCreditLimit = creditLimitService.createCreditLimit(expectedCustomer);

        // valid step
        assertEquals(null, actualCreditLimit);
    }

    @Test
    void createCreditLimit_lowestCreditScore() {

        // init step
        Customer expectedCustomer = new Customer(7,"77777777777","Sefa","Altundal",4000,"5324992217");
        CreditLimit expectedCreditLimit = new CreditLimit(null, "77777777777", 0);
        // stub - when step
        when(creditScoreRepository.getCreditScoreByIdentityNumber(expectedCustomer.getIdentityNumber())).thenReturn(new CreditScore(1,"77777777777",499));

        // then step
        CreditLimit actualCreditLimit = creditLimitService.createCreditLimit(expectedCustomer);

        // valid step
        assertEquals(expectedCreditLimit, actualCreditLimit);
    }

    @Test
    void createCreditLimit_mediumCreditScore_lowestIncome() {

        // init step
        Customer expectedCustomer = new Customer(7,"77777777777","Sefa","Altundal",4000,"5324992217");
        CreditLimit expectedCreditLimit = new CreditLimit(null, "77777777777", 10000);
        // stub - when step
        when(creditScoreRepository.getCreditScoreByIdentityNumber(expectedCustomer.getIdentityNumber())).thenReturn(new CreditScore(1,"77777777777",500));

        // then step
        CreditLimit actualCreditLimit = creditLimitService.createCreditLimit(expectedCustomer);

        // valid step
        assertEquals(expectedCreditLimit, actualCreditLimit);
    }

    @Test
    void createCreditLimit_mediumCreditScore_mediumIncome() {

        // init step
        Customer expectedCustomer = new Customer(7,"77777777777","Sefa","Altundal",5000,"5324992217");
        CreditLimit expectedCreditLimit = new CreditLimit(null, "77777777777", 20000);

        // stub - when step
        when(creditScoreRepository.getCreditScoreByIdentityNumber(expectedCustomer.getIdentityNumber())).thenReturn(new CreditScore(1,"77777777777",500));

        // then step
        CreditLimit actualCreditLimit = creditLimitService.createCreditLimit(expectedCustomer);

        // valid step
        assertEquals(expectedCreditLimit, actualCreditLimit);
    }

    @Test
    void createCreditLimit_highCreditScore() {

        // init step
        Customer expectedCustomer = new Customer(7,"77777777777","Sefa","Altundal",2500,"5324992217");
        CreditLimit expectedCreditLimit = new CreditLimit(null, "77777777777", expectedCustomer.getMonthlyIncome()*4);

        // stub - when step
        when(creditScoreRepository.getCreditScoreByIdentityNumber(expectedCustomer.getIdentityNumber())).thenReturn(new CreditScore(1,"77777777777",1000));

        // then step
        CreditLimit actualCreditLimit = creditLimitService.createCreditLimit(expectedCustomer);

        // valid step
        assertEquals(expectedCreditLimit, actualCreditLimit);
    }

    @Test
    void getCreditLimit_successful() {
        // init step
        CreditLimit expectedCreditLimit = new CreditLimit(null,"77777777777",1300);

        // stub - when step
        when(creditLimitRepository.getCreditLimitByIdentityNumber("77777777777")).thenReturn(expectedCreditLimit);

        // then step
        CreditLimit actualCreditLimit = creditLimitService.getCreditLimit("77777777777");

        // valid step
        assertEquals(expectedCreditLimit, actualCreditLimit);

    }
    @Test
    void getCreditLimit_notFound() {


        // stub - when step
        when(creditLimitRepository.getCreditLimitByIdentityNumber("77777777777")).thenReturn(null);

        // then step
        CreditLimit actualCreditLimit = creditLimitService.getCreditLimit("77777777777");

        // valid step
        assertEquals(null, actualCreditLimit);

    }


}