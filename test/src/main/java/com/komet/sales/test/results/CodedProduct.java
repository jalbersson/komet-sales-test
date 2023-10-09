package com.komet.sales.test.results;

public class CodedProduct {
    private String productName;
    private String productCode;

    public CodedProduct(String productName, String productCode) {
        this.productName = productName;
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
