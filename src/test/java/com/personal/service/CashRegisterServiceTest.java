package com.personal.service;

import com.personal.entity.Summary;
import com.personal.repository.SummaryRepository;
import com.personal.repository.TransactionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CashRegisterServiceTest {

    //We Autowired the AccountService bean so that it is injected from the configuration
    @Autowired
    private CashRegisterService cashRegisterService;
    @Autowired
    private SummaryRepository summaryRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private Summary getSummaryForInit() {
        return new Summary.Builder()
                .countOne(5).countTwo(4).countFive(3).countTen(2).countTwenty(1)
                .valueOne(5).valueTwo(8).valueFive(15).valueTen(20).valueTwenty(20).valueSum(68)
                .build();

    }

    @Before
    public void setup() {
        List<Summary> list = Arrays.asList(getSummaryForInit());
        when(summaryRepository.findAll(new Sort(Sort.Direction.DESC, "id"))).thenReturn(list);
    }

    @After
    public void verify() {
        Mockito.verify(summaryRepository, VerificationModeFactory.times(1)).findAll(new Sort(Sort.Direction.DESC, "id"));
        // This is allowed here: using container injected mocks
        Mockito.reset(summaryRepository);
    }

    @Test
    public void findLastSummaryTest() {
        Summary s = cashRegisterService.findLastSummary();
        assertEquals(1, s.getCountTwenty());
        assertEquals(2, s.getCountTen());
        assertEquals(3, s.getCountFive());
        assertEquals(4, s.getCountTwo());
        assertEquals(5, s.getCountOne());
        assertEquals(5, s.getValueOne());
        assertEquals(8, s.getValueTwo());
        assertEquals(15, s.getValueFive());
        assertEquals(20, s.getValueTen());
        assertEquals(20, s.getValueTwenty());
        assertEquals(68, s.getValueSum());
    }

    @Configuration
    static class CashRegisterServiceTestContextConfiguration {
        @Bean
        public CashRegisterService cashRegisterService() {
            return new CashRegisterServiceImpl();
        }

        @Bean
        public SummaryRepository summaryRepository() {
            return Mockito.mock(SummaryRepository.class);
        }

        @Bean
        public TransactionRepository transactionRepository() {
            return Mockito.mock(TransactionRepository.class);
        }
    }

}

