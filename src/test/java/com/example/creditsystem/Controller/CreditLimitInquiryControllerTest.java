package com.example.creditsystem.Controller;

import com.example.creditsystem.Exception.handler.GenericExceptionHandler;
import com.example.creditsystem.Model.Entity.CreditLimit;
import com.example.creditsystem.Model.Entity.Customer;
import com.example.creditsystem.Service.CreditLimitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(MockitoExtension.class)
class CreditLimitInquiryControllerTest {

    private MockMvc mvc;

    @Mock
    private CreditLimitService creditLimitService;

    @InjectMocks
    private CreditLimitInquiryController creditLimitInquiryController;

    @BeforeEach
    public void setup(){
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(creditLimitInquiryController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }
    @Test
    void creditInquiry_NotFoundCreditScore() throws Exception {

        Customer expectedCustomer = new Customer(6,"66666666666","Sefa","Altundal",10000,"5324992216");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedCreditLimitsJsonStr = ow.writeValueAsString(expectedCustomer);
        // stub - when
        when(creditLimitService.createCreditLimit(expectedCustomer)).thenReturn(null);

        MockHttpServletResponse response = mvc.perform(post("/api/credit-score-inquiry/get-credit-limit")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedCreditLimitsJsonStr))
                .andDo(print())
                .andReturn().getResponse();
        // then
        assertThat(response.getContentAsString()).isEqualTo("com.example.rest.error.credit_score_not_found");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());


    }

    @Test
    void creditInquiry_Rejected() throws Exception {

        Customer expectedCustomer = new Customer(9,"99999999999","Sefa","Altundal",10000,"5324992219");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedCreditLimitsJsonStr = ow.writeValueAsString(expectedCustomer);
        //when
        when(creditLimitService.createCreditLimit(expectedCustomer)).thenReturn(new CreditLimit(9,"99999999999",0));

        MockHttpServletResponse response = mvc.perform(post("/api/credit-score-inquiry/get-credit-limit")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedCreditLimitsJsonStr))
                .andDo(print())
                .andReturn().getResponse();
        // then
        assertThat(response.getContentAsString()).isEqualTo("com.example.rest.response.credit_application_rejected");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());

    }

    @Test
    void creditInquiry_Accepted() throws Exception {
        Customer expectedCustomer = new Customer(9,"99999999999","Sefa","Altundal",10000,"5324992219");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedCreditLimitsJsonStr = ow.writeValueAsString(expectedCustomer);
        //when
        when(creditLimitService.createCreditLimit(expectedCustomer)).thenReturn(new CreditLimit(9,"99999999999",40000));

        MockHttpServletResponse response = mvc.perform(post("/api/credit-score-inquiry/get-credit-limit")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedCreditLimitsJsonStr))
                .andDo(print())
                .andReturn().getResponse();
        // then
        assertThat(response.getContentAsString()).isEqualTo("{\"id\":9,\"identityNumber\":\"99999999999\",\"creditLimit\":40000}");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

}