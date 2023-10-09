package com.komet.sales.test.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tblinventorypt")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cubespercarrier")
    private BigDecimal cubesPerCarrier;

    @Column
    private int pack;
    @Column(name = "baseprice")
    private BigDecimal basePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boxtypeid")
    private BoxType boxType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyid")
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPack() {
        return pack;
    }

    public void setPack(int pack) {
        this.pack = pack;
    }

    public BigDecimal getCubesPerCarrier() {
        return cubesPerCarrier;
    }

    public void setCubesPerCarrier(BigDecimal cubesPerCarrier) {
        this.cubesPerCarrier = cubesPerCarrier;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
