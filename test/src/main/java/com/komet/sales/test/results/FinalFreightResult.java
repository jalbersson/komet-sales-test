package com.komet.sales.test.results;

import java.util.List;

public class FinalFreightResult {
    private Long companyId;
    private List<ProductFinalFreight> products;

    public FinalFreightResult(Long companyId, List<ProductFinalFreight> products) {
        this.companyId = companyId;
        this.products = products;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<ProductFinalFreight> getProducts() {
        return products;
    }

    public void setProducts(List<ProductFinalFreight> products) {
        this.products = products;
    }
}
