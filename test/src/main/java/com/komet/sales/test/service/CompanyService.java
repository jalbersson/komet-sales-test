package com.komet.sales.test.service;

import com.komet.sales.test.model.Company;
import com.komet.sales.test.repository.ICompanyRepository;
import com.komet.sales.test.results.ProductFinalFreight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private ICompanyRepository repository;

    /**
     * Calculates the final freight value based on the company's id
     * @param companyId: the id of the company
     * @return a list containing the company's products and their final freight value
     */
    public List<ProductFinalFreight> getFinalFreights(Long companyId){
        Optional<Company> companyOptional = repository.findById(companyId);
        List<ProductFinalFreight> result = new ArrayList<>();

        // verify if there is any company with the parameter companyId
        if(companyOptional.isPresent()){
            List<Object[]> data = repository.getFinalFreightsData(companyId);

            // extract the data
            for (Object[] row : data) {
                String productName = (String) row[0];
                BigDecimal basePrice = (BigDecimal) row[1];
                BigDecimal finalFreight = (BigDecimal) row[2];

                ProductFinalFreight productFinalFreight = new ProductFinalFreight(productName, basePrice, finalFreight);
                result.add(productFinalFreight);
            }
        }

        return result;
    }
}
