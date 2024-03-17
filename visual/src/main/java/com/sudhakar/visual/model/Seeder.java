package com.sudhakar.visual.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.sudhakar.visual.repository.UserRepository;

@Component
public class Seeder implements CommandLineRunner {

    private static final int DAYS_IN_PAST = 1000;
    int RESET_VALUES_INTERVAL = 200;

    private final UserRepository userRepository;
    private final Faker faker;

    private String repeatedDistrict;
    private String repeatedPinCode;
    private String repeatedState;
    private String repeatedLatitude;
    private String repeatedLongitude;

    public Seeder(UserRepository userRepository, Faker faker) {
        this.userRepository = userRepository;
        this.faker = faker;
        resetRepeatedValues();
    }

    private void resetRepeatedValues() {
        this.repeatedDistrict = faker.address().city();
        this.repeatedPinCode = faker.address().zipCode();
        this.repeatedState = faker.address().state();
        this.repeatedLatitude = faker.address().latitude();
        this.repeatedLongitude = faker.address().longitude();
    }

    private void generateTestDataOnce() {
        if (!hasTestDataBeenGenerated()) {
            createUsersWithFakeData(5000);
            markTestDataAsGenerated();
        } else {
            System.out.println("Test data has already been generated.");
        }
    }

    private boolean hasTestDataBeenGenerated() {
        return userRepository.existsByEmail("testDataGenerated@example.com");
    }

    @SuppressWarnings("null")
    private void markTestDataAsGenerated() {
        User testDataUser = User.builder()
                .firstName("Test")
                .lastName("Data")
                .email("testDataGenerated@example.com")
                .build();
        userRepository.save(testDataUser);
    }

    @SuppressWarnings("null")
    private void createUsersWithFakeData(int numberOfUsers) {
        for (int i = 0; i < numberOfUsers; i++) {
            User user = generateFakeUser(i);
            userRepository.save(user);
        }
    }

    private User generateFakeUser(int index) {
        if (index % RESET_VALUES_INTERVAL == 0) {
            RESET_VALUES_INTERVAL = (int)(Math.random() * 500);
            resetRepeatedValues();
        }

        LocalDateTime createdAt = faker.date().past(DAYS_IN_PAST, TimeUnit.DAYS).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
        Date from = Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Date loggedInAt = faker.date().between(from, to);
        LocalDateTime deletedAt = (index % 10 == 0) ? faker.date().past(DAYS_IN_PAST, TimeUnit.DAYS).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime() : null;

        return User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .createdAt(createdAt)
                .loggedInAt(loggedInAt)
                .deletedAt(deletedAt)
                .updatedAt(faker.date().past(DAYS_IN_PAST, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .district(repeatedDistrict)
                .pinCode(repeatedPinCode)
                .state(repeatedState)
                .latitude(repeatedLatitude)
                .longitude(repeatedLongitude)
                .build();
    }

    @Override
    public void run(String... args) throws Exception {
        generateTestDataOnce();
    }
}
