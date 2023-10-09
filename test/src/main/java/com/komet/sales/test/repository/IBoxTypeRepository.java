package com.komet.sales.test.repository;

import com.komet.sales.test.model.BoxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoxTypeRepository extends JpaRepository<BoxType, Long> {
}
