package com.extraction.controller;

import com.extraction.model.EntityDto;
import com.extraction.service.HotelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody EntityDto hotelDto) throws JsonProcessingException {
        return new ResponseEntity<>(hotelService.exportData(hotelDto), HttpStatus.OK);
    }
}
