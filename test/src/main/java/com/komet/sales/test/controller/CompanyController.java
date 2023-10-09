package com.komet.sales.test.controller;

import com.komet.sales.test.exception.InternalServerException;
import com.komet.sales.test.results.FinalFreightResult;
import com.komet.sales.test.results.ProductFinalFreight;
import com.komet.sales.test.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "company")
public class CompanyController {
    @Autowired
    private CompanyService service;

    /**
     * solution to the first point. The id of the company should be sent int the
     * request path. Calculates the final freight value based on the company's id
     * and creates an appropriate result for the response
     * @param companyId: the id of the company
     * @return an appropriate result for the response. An internal Server Error
     * in case there is any exception during the execution of the task
     */
    @GetMapping("/getFinalFreights/{companyId}")
    public ResponseEntity<Object> getFinalFreights(@PathVariable("companyId") Long companyId){
        try {
            List<ProductFinalFreight> finalFreightResults = service.getFinalFreights(companyId);
            if(!finalFreightResults.isEmpty()){
                FinalFreightResult finalFreightResult = new FinalFreightResult(companyId, finalFreightResults);
                return new ResponseEntity<>(finalFreightResult, HttpStatus.OK);
            } else {
                return ResponseEntity.noContent().build();
            }


        } catch (Exception exception) {
            throw new InternalServerException(exception.getMessage());
        }
    }
}
