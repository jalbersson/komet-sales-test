package com.komet.sales.test.results;

import java.math.BigDecimal;

public class CompanyProduct {
    private String productName;
    private String companyName;
    private BigDecimal price;

    public CompanyProduct(String productName, String companyName, BigDecimal price) {
        this.productName = productName;
        this.companyName = companyName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
