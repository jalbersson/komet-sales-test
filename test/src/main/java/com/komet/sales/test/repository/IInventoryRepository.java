package com.komet.sales.test.repository;

import com.komet.sales.test.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("SELECT pr.name AS productName, cp.name AS companyName, inv.basePrice AS basePrice " +
            "FROM Product AS pr CROSS JOIN Inventory AS inv CROSS JOIN Company AS cp " +
            "WHERE cp.id = inv.company.id AND pr.id = inv.product.id ORDER BY companyName")
    List<Object[]> getProductsCompany();

    @Query("SELECT DISTINCT pr.name AS productName " +
            "FROM Product AS pr CROSS JOIN Inventory AS inv CROSS JOIN Company AS cp " +
            "WHERE cp.id = inv.company.id AND pr.id = inv.product.id AND cp.id = :companyId")
    List<Object[]> getProductsFromCompany(@Param("companyId") Long companyId);
}
