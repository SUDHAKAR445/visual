package com.sudhakar.visual.controller;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudhakar.visual.model.MapMaker;
import com.sudhakar.visual.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/chart")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/register/{inputYear}")
    public ResponseEntity<Map<String, Long>> getDataForOneYear(@PathVariable int inputYear){
        return userService.generateChartData(inputYear);
    }

    @GetMapping("/delete/{inputYear}")
    public ResponseEntity<Map<String, Long>> getDeleteDataForOneYear(@PathVariable int inputYear){
        return userService.generateDeleted(inputYear);
    }

    @GetMapping("/district")
    public ResponseEntity<Map<String, Long>> getDistrictViceData(){
        return userService.getUserByDistrictVice();
    }

    @GetMapping
    public ResponseEntity<List<MapMaker>> getAllMapMaker() {
        return userService.getMarkers();
    }

    @GetMapping("/month/{month}/{year}")
    public ResponseEntity<Map<String, Long>> getUserInDateVice(@PathVariable Month month, @PathVariable int year) {
        return userService.generateChartDataMonth(month, year);
    }

    @GetMapping("/delete/{month}/{year}")
    public ResponseEntity<Map<String, Long>> getDeleteUserInDateVice(@PathVariable Month month, @PathVariable int year) {
        return userService.generateChartDeleteDataMonth(month, year);
    }
}
