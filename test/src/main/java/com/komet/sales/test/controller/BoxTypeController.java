package com.komet.sales.test.controller;

import com.komet.sales.test.exception.InternalServerException;
import com.komet.sales.test.model.BoxType;
import com.komet.sales.test.service.BoxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/boxtype")
public class BoxTypeController {
    @Autowired
    private BoxTypeService service;

    @GetMapping("/getAllBoxTypes")
    public ResponseEntity<Object> getAllBoxTypes(){
        try {
            List<BoxType> boxTypes = service.getAllBoxTypes();

            return new ResponseEntity<>(boxTypes, HttpStatus.OK);
        } catch (Exception exception) {
            throw new InternalServerException(exception.getMessage());
        }
    }
}
