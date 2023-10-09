package com.komet.sales.test.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tblcustomerpt")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal markdown;

    public Customer() {
    }

    public Customer(Long id, String name, BigDecimal markdown) {
        this.id = id;
        this.name = name;
        this.markdown = markdown;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMarkdown() {
        return markdown;
    }

    public void setMarkdown(BigDecimal markdown) {
        this.markdown = markdown;
    }
}
