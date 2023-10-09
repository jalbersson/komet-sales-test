package com.komet.sales.test.controller;

import com.komet.sales.test.exception.InternalServerException;
import com.komet.sales.test.results.CodedProduct;
import com.komet.sales.test.results.CompanyProduct;
import com.komet.sales.test.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "inventory")
public class InventoryController {
    @Autowired
    InventoryService service;

    /**
     * Solution to the second task. The customer id should be sent in the request path.
     * Calculates the adequate price of the products depending on the customer id and
     * creates an appropriate result for the response
     * @param customerId: the id of the customer
     * @return an object containing the results as it was asked. An internal Server Error
     * in case there is any exception during the execution of the task
     */
    @GetMapping("/getProductsCompany/{customerId}")
    public ResponseEntity<Object> getProductsCompany(@PathVariable("customerId") Long customerId){
        try {
            List<CompanyProduct> companyProducts = service.getProductsCompany(customerId);
            if(!companyProducts.isEmpty()){
                return new ResponseEntity<>(companyProducts, HttpStatus.OK);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception exception) {
            throw new InternalServerException(exception.getMessage());
        }
    }

    /**
     * Solution to the third task. The company id should be sent in the request path.
     * Creates a list of products names and their coded versions associated to a company
     * creates an appropriate result for the response
     * @param companyId: the id of the company
     * @return an object containing the results as it was asked. An internal Server Error
     * in case there is any exception during the execution of the task
     */
    @GetMapping("/getProductsCoded/{companyId}")
    public ResponseEntity<Object> getProductsCoded(@PathVariable("companyId") Long companyId){
        try {
            List<CodedProduct> codedProducts = service.getProductsCoded(companyId);
            if(!codedProducts.isEmpty()){
                return new ResponseEntity<>(codedProducts, HttpStatus.OK);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception exception) {
            throw new InternalServerException(exception.getMessage());
        }
    }
}
