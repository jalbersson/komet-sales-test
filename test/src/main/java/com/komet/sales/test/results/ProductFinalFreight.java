package com.komet.sales.test.results;

import java.math.BigDecimal;

public class ProductFinalFreight {
    private String productName;
    private BigDecimal basePrice;
    private BigDecimal  finalFreight;

    public ProductFinalFreight(String productName, BigDecimal basePrice, BigDecimal finalFreight) {
        this.productName = productName;
        this.basePrice = basePrice;
        this.finalFreight = finalFreight;
    }
    /*public ProductFinalFreight(String productName, BigDecimal basePrice) {
        this.productName = productName;
        this.basePrice = basePrice;
    }*/


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getFinalFreight() {
        return finalFreight;
    }

    public void setFinalFreight(BigDecimal finalFreight) {
        this.finalFreight = finalFreight;
    }
}
