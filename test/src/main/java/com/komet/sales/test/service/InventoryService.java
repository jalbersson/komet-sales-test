package com.komet.sales.test.service;

import com.komet.sales.test.model.Company;
import com.komet.sales.test.model.Customer;
import com.komet.sales.test.repository.ICompanyRepository;
import com.komet.sales.test.repository.ICustomerRepository;
import com.komet.sales.test.repository.IInventoryRepository;
import com.komet.sales.test.results.CodedProduct;
import com.komet.sales.test.results.CompanyProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class InventoryService {
    @Autowired
    IInventoryRepository repository;

    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    ICompanyRepository companyRepository;

    /**
     * Calculates the product price applied to a specific customer depending on his
     * markdown value
     * @param customerId: the id of the customer
     * @return a list containing the products name, the company and the final price of the product
     */
    public List<CompanyProduct> getProductsCompany(Long customerId){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Customer customer = null;
        List<CompanyProduct> result = new ArrayList<>();

        // verify if there is any customer with the parameter customerId
        if(customerOptional.isPresent()){
            customer = customerOptional.get();

            // First get all products and their associated company
            List<Object[]> data = repository.getProductsCompany();

            for (Object[] row : data) {
                String productName = (String) row[0];
                String companyName = (String) row[1];
                BigDecimal basePrice = (BigDecimal) row[2];

                // customer.getMarkdown() is set as not null in the database, so is not necessary to verify
                BigDecimal price = basePrice.subtract(basePrice.multiply
                        (customer.getMarkdown().divide(new BigDecimal("100"))));

                CompanyProduct companyProduct = new CompanyProduct(productName, companyName, price);
                result.add(companyProduct);
            }
        }

        return result;
    }

    /**
     * extract from database the products associated to a company and creates coded
     * names for the products name
     * @param companyId: the id of the company that offer some products
     * @return a list of product names and their codes
     */
    public List<CodedProduct> getProductsCoded(Long companyId){
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        List<CodedProduct> result = new ArrayList<>();

        // verify if there is any company with the parameter companyId
        if(companyOptional.isPresent()){

            // First get all products that the company offers
            List<Object[]> data = repository.getProductsFromCompany(companyId);

            for (Object[] row : data) {
                String productName = (String) row[0];

                // start creating the product code
                String productCode = "";
                String [] words = productName.split(" ");
                for(int i = 0; i < words.length; i++){
                    productCode += createCodeFromString(words[i]);
                    productCode += "-";
                }
                // eliminate the final '-' that will be present in the code
                productCode = productCode.substring(0, productCode.length() -1);

                CodedProduct codedProduct = new CodedProduct(productName, productCode);
                result.add(codedProduct);
            }
        }

        return result;
    }

    /**
     * transforms a string into a coded version of it
     * @param initialString
     * @return a coded version of initialString
     */
    private String createCodeFromString(String initialString){
        String codedString = "";
        codedString += initialString.charAt(0);
        String trimmedString = initialString.substring(1);
        List<String> specialChars = new ArrayList<>();
        Set<String> differentChars = new HashSet<>();
        char lastChar;
        if(trimmedString.length() >= 3){
            lastChar = trimmedString.charAt(trimmedString.length() - 1);
            trimmedString = trimmedString.substring(0, trimmedString.length() - 1);

            // extract the special chars
            for (int i = 0; i < trimmedString.length(); i++) {
                char currentChar = trimmedString.charAt(i);

                // Verify if the char is special
                if (!Character.isLetterOrDigit(currentChar)) {
                    specialChars.add(currentChar + "");
                }

                // add the distinct chars to the set
                if(!differentChars.contains(currentChar + "")){
                    differentChars.add(currentChar + "");
                }
            }

            // build the coded string
            codedString += differentChars.size() + "";
            for(String specialChar : specialChars){
                codedString += specialChar;
            }
            codedString += lastChar;
        } else if(trimmedString.length() == 2){
            codedString += "1" + trimmedString.charAt(1);
        } else {
            codedString += "0" + trimmedString.charAt(0);
        }


        return codedString;
    }
}
