package com.example.creditsystem.Service.iml;

import com.example.creditsystem.Exception.NotFoundException;
import com.example.creditsystem.Model.Entity.CreditLimit;
import com.example.creditsystem.Model.Entity.CreditScore;
import com.example.creditsystem.Repository.CreditLimitRepository;
import com.example.creditsystem.Repository.CreditScoreRepository;
import com.example.creditsystem.Service.CreditScoreService;
import com.example.creditsystem.Service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)



class CreditScoreServiceImplTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private CreditLimitRepository creditLimitRepository;

    @Mock
    private CreditScoreRepository creditScoreRepository;

    @InjectMocks
    private CreditScoreServiceImpl creditScoreService;

    @Test
    void getCreditScore_successful() {
        // init
        CreditScore expectedCreditScore = new CreditScore(1,"11111111111",1200);

        //stub-when step
        Optional<CreditScore>  expectedOptCreditScore = Optional.of(expectedCreditScore);
        when(creditScoreRepository.findById(1)).thenReturn(expectedOptCreditScore);

        //than
        CreditScore actualCreditScore = creditScoreService.getCreditScore(1);

        //valid Step
        assertEquals(expectedCreditScore,actualCreditScore);
    }

    @Test
    void getCreditScore_notfound() {
        // stub - when step
        when(creditScoreRepository.findById(1)).thenReturn(Optional.empty());

        // then step
        assertThrows(NotFoundException.class,
                () -> {
                    CreditScore actualCreditScore = creditScoreService.getCreditScore(1);
                }
        );

    }

    @Test
    void addCreditScore() {
        // init
        CreditScore expectedCreditScore = new CreditScore(1,"11111111111",1200);

        //stub-when step
        when(creditScoreRepository.save(expectedCreditScore)).thenReturn(expectedCreditScore);


        //than
        creditScoreService.addCreditScore(expectedCreditScore);

        //valid Step
        verify(creditScoreRepository, times(1)).save(expectedCreditScore);
    }


}