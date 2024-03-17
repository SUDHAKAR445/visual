package com.sudhakar.visual.service.implementation;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sudhakar.visual.model.MapMaker;
import com.sudhakar.visual.model.User;
import com.sudhakar.visual.repository.UserRepository;
import com.sudhakar.visual.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    Month[] monthsInOrder = {
            Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL,
            Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST,
            Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER
    };

    @Override
    public ResponseEntity<Map<String, Long>> generateChartData(int inputYear) {
        try {
            YearMonth inputYearMonth = YearMonth.of(inputYear, Month.JANUARY);
            YearMonth nextYearMonth = inputYearMonth.plusYears(1);

            List<User> users = userRepository.findAll().stream()
                    .filter(user -> user.getCreatedAt() != null &&
                            (user.getCreatedAt().isAfter(inputYearMonth.atDay(1).atStartOfDay()) &&
                                    user.getCreatedAt().isBefore(nextYearMonth.atDay(1).atStartOfDay())))
                    .collect(Collectors.toList());

            Map<Month, Long> countByMonth = users.stream()
                    .filter(user -> user.getCreatedAt() != null)
                    .collect(Collectors.groupingBy(user -> user.getCreatedAt().getMonth(), Collectors.counting()));

            Map<String, Long> chartDataMap = new LinkedHashMap<>();
            for (Month month : monthsInOrder) {
                String monthLabel = month.toString();
                chartDataMap.put(monthLabel, countByMonth.getOrDefault(month, 0L));
            }

            return new ResponseEntity<>(chartDataMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Map<String, Long>> generateDeleted(int inputYear) {
        try {
            YearMonth inputYearMonth = YearMonth.of(inputYear, Month.JANUARY);
            YearMonth nextYearMonth = inputYearMonth.plusYears(1);

            List<User> users = userRepository.findAll().stream()
                    .filter(user -> user.getDeletedAt() != null &&
                            (user.getDeletedAt().isAfter(inputYearMonth.atDay(1).atStartOfDay()) &&
                                    user.getDeletedAt().isBefore(nextYearMonth.atDay(1).atStartOfDay())))
                    .collect(Collectors.toList());

            Map<Month, Long> countByMonth = users.stream()
                    .filter(user -> user.getDeletedAt() != null)
                    .collect(Collectors.groupingBy(user -> user.getDeletedAt().getMonth(), Collectors.counting()));

            Map<String, Long> chartDataMap = new LinkedHashMap<>();
            for (Month month : monthsInOrder) {
                String monthLabel = month.toString();
                chartDataMap.put(monthLabel, countByMonth.getOrDefault(month, 0L));
            }

            return new ResponseEntity<>(chartDataMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Map<String, Long>> getUserByDistrictVice() {
        try {
            List<User> users = userRepository.findAll();
            Map<String, Long> district = new LinkedHashMap<>();
            for (User user : users) {
                if (user.getDistrict() != null) {
                    if (district.containsKey(user.getDistrict())) {

                        district.put(user.getDistrict(), district.get(user.getDistrict()) + 1);
                    } else {
                        district.put(user.getDistrict(), 1L);
                    }
                }
            }
            return new ResponseEntity<>(district, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<MapMaker>> getMarkers() {
        try {
            List<User> users = userRepository.findAll();

            Map<String, MapMaker> markerMap = new HashMap<>();

            for (User user : users) {
                if (user.getLatitude() != null) {
                    String key = user.getLatitude() + "," + user.getLongitude();
                    if (markerMap.containsKey(key)) {
                        MapMaker existingMarker = markerMap.get(key);
                        existingMarker.setPopulation(existingMarker.getPopulation() + 1);
                    } else {
                        MapMaker newMarker = new MapMaker(
                                user.getDistrict() + ", " + user.getState(),
                                Double.parseDouble(user.getLatitude()),
                                Double.parseDouble(user.getLongitude()),
                                1,
                                "blue",
                                "InvertedTriangle");
                        markerMap.put(key, newMarker);
                    }
                }
            }

            return new ResponseEntity<>(new ArrayList<>(markerMap.values()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Map<String, Long>> generateChartDataMonth(Month month, int inputYear) {
        try {
            YearMonth inputYearMonth = YearMonth.of(inputYear, month);

            List<User> users = userRepository.findAll().stream()
                    .filter(user -> user.getCreatedAt() != null &&
                            user.getCreatedAt().getMonth() == month &&
                            user.getCreatedAt().getYear() == inputYear)
                    .collect(Collectors.toList());

            Map<String, Long> chartDataMap = new TreeMap<>();
            LocalDate startDate = inputYearMonth.atDay(1);
            LocalDate endDate = inputYearMonth.atEndOfMonth();

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                chartDataMap.put(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 0L);
            }

            users.stream()
                    .filter(user -> user.getCreatedAt() != null)
                    .collect(Collectors.groupingBy(
                            user -> user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            TreeMap::new,
                            Collectors.counting()))
                    .forEach(chartDataMap::put);

            return new ResponseEntity<>(chartDataMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Map<String, Long>> generateChartDeleteDataMonth(Month month, int inputYear) {
        try {
            YearMonth inputYearMonth = YearMonth.of(inputYear, month);

            List<User> users = userRepository.findAll().stream()
                    .filter(user -> user.getDeletedAt() != null &&
                            user.getDeletedAt().getMonth() == month &&
                            user.getDeletedAt().getYear() == inputYear)
                    .collect(Collectors.toList());

            Map<String, Long> chartDataMap = new TreeMap<>();
            LocalDate startDate = inputYearMonth.atDay(1);
            LocalDate endDate = inputYearMonth.atEndOfMonth();

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                chartDataMap.put(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 0L);
            }

            users.stream()
                    .filter(user -> user.getCreatedAt() != null)
                    .collect(Collectors.groupingBy(
                            user -> user.getDeletedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            TreeMap::new,
                            Collectors.counting()))
                    .forEach(chartDataMap::put);

            return new ResponseEntity<>(chartDataMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}