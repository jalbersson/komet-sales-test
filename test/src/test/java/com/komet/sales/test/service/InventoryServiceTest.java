package com.komet.sales.test.service;

import com.komet.sales.test.model.Company;
import com.komet.sales.test.model.Customer;
import com.komet.sales.test.repository.ICompanyRepository;
import com.komet.sales.test.repository.ICustomerRepository;
import com.komet.sales.test.repository.IInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class InventoryServiceTest {
    @Mock
    private IInventoryRepository repository;

    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private ICompanyRepository companyRepository;

    @InjectMocks
    private InventoryService service;

    private List<Object[]> productsCompanyResultList;

    private List<Object[]> productsCodedResultList;

    private Customer resultCustomer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // prepare getProductsCompany results
        Object[] productsCompanyObtainedRows = new Object[]{"IL Hydrangea Blue", "BellaFlowers", new BigDecimal("1.2740")};
        productsCompanyResultList = new ArrayList<>();
        productsCompanyResultList.add(productsCompanyObtainedRows);
        resultCustomer = new Customer(1L, "Daniel", new BigDecimal("2.00"));

        // prepare getProductsCoded results
        Object[] productsCodedObtainedRows = new Object[]{"IL Hydrangea Blue"};
        productsCodedResultList = new ArrayList<>();
        productsCodedResultList.add(productsCodedObtainedRows);
    }

    @Test
    void getProductsCompany() {
        when(repository.getProductsCompany()).thenReturn(productsCompanyResultList);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(resultCustomer));

        // successful common scenario
        assertNotNull(service.getProductsCompany(1L));
        assertEquals("IL Hydrangea Blue", service.getProductsCompany(1L).get(0).getProductName());
        assertEquals("BellaFlowers", service.getProductsCompany(1L).get(0).getCompanyName());
        assertEquals(new BigDecimal("1.248520"), service.getProductsCompany(1L).get(0).getPrice());

        // unsuccessful scenario where the customerId doesn't exist on database
        when(customerRepository.findById(3L)).thenReturn(Optional.empty());
        assertNotNull(service.getProductsCompany(3L));
        assertEquals(0, service.getProductsCompany(3L).size());
    }

    @Test
    void getProductsCoded() {
        when(companyRepository.findById(4L)).thenReturn(Optional.of(new Company(4L, "CandyFlowers")));
        when(repository.getProductsFromCompany(4L)).thenReturn(productsCodedResultList);

        // successful common scenario
        assertNotNull(service.getProductsCoded(4L));
        assertEquals("IL Hydrangea Blue", service.getProductsCoded(4L).get(0).getProductName());
        assertEquals("I0L-H7a-B2e", service.getProductsCoded(4L).get(0).getProductCode());

        // unsuccessful scenario where the companyId doesn't exist on database
        when(companyRepository.findById(6L)).thenReturn(Optional.empty());
        assertNotNull(service.getProductsCoded(6L));
        assertEquals(0, service.getProductsCoded(6L).size());
    }
}