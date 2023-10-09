package com.komet.sales.test.repository;

import com.komet.sales.test.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT pr.name AS productName, inv.basePrice AS basePrice, " +
            "(((inv.cubesPerCarrier * ((box.width * box.height * box.length) / 1728)) / inv.pack) * (pr.freshCutValue / 100)) AS finalFreight " +
            "FROM Product AS pr CROSS JOIN Inventory as inv CROSS JOIN BoxType AS box " +
            "WHERE pr.id = inv.product.id AND box.id = inv.boxType.id AND inv.company.id = :companyId")
    List<Object[]> getFinalFreightsData(@Param("companyId") Long companyId);
}
