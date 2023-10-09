package com.komet.sales.test.service;

import com.komet.sales.test.repository.ICompanyRepository;
import com.komet.sales.test.results.ProductFinalFreight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CompanyServiceTest {
    @Mock
    private ICompanyRepository repository;

    @InjectMocks
    private CompanyService service;

    private List<Object[]> resultList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // prepare the repository method results
        Object[] obtainedRows = new Object[]{"Red Roses 23cm", new BigDecimal("1.70000"), new BigDecimal("1.87833")};
        resultList = new ArrayList<>();
        resultList.add(obtainedRows);
    }

    @Test
    void getFinalFreights() {
        when(repository.getFinalFreightsData(2L)).thenReturn(resultList);

        // successful common scenario
        assertNotNull(service.getFinalFreights(2L));
        assertEquals("Red Roses 23cm", service.getFinalFreights(2L).get(0).getProductName());
        assertEquals(new BigDecimal("1.70000"), service.getFinalFreights(2L).get(0).getBasePrice());
        assertEquals(new BigDecimal("1.87833"), service.getFinalFreights(2L).get(0).getFinalFreight());

        // unsuccessful scenario where the companyId doesn't exist on database
        when(repository.getFinalFreightsData(3L)).thenReturn(Collections.emptyList());
        assertNotNull(service.getFinalFreights(3L));
        assertEquals(0, service.getFinalFreights(3L).size());

    }
}