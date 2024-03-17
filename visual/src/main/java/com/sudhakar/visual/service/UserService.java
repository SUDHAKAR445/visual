package com.sudhakar.visual.service;

import java.time.Month;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.sudhakar.visual.model.MapMaker;

public interface UserService {

    ResponseEntity<Map<String, Long>> generateChartData(int inputYear);

    ResponseEntity<Map<String, Long>> generateDeleted(int inputYear);

    ResponseEntity<Map<String, Long>> getUserByDistrictVice();

    ResponseEntity<List<MapMaker>> getMarkers();

    ResponseEntity<Map<String, Long>> generateChartDataMonth(Month month, int inputYear);

    ResponseEntity<Map<String, Long>> generateChartDeleteDataMonth(Month month, int inputYear);

}
