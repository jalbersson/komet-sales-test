package com.komet.sales.test.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblproductpt")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "freshcutvalue")
    private BigDecimal freshCutValue;

    @OneToMany(mappedBy = "product")
    private Set<Inventory> inventories = new HashSet<>();

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

    public BigDecimal getFreshCutValue() {
        return freshCutValue;
    }

    public void setFreshCutValue(BigDecimal freshCutValue) {
        this.freshCutValue = freshCutValue;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }
}
