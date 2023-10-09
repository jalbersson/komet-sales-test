package com.komet.sales.test.service;

import com.komet.sales.test.model.BoxType;
import com.komet.sales.test.repository.IBoxTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxTypeService {
    @Autowired
    private IBoxTypeRepository repository;

    public List<BoxType> getAllBoxTypes(){
        return repository.findAll();
    }
}
