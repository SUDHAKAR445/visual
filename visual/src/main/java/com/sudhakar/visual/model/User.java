package com.sudhakar.visual.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("email")
    private String email;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("logged_in_at")
    private Date loggedInAt;

    @Field("deleted_at")
    private LocalDateTime deletedAt;

    @Field("updated_at")
    private LocalDateTime updatedAt;

    @Field("district")
    private String district;

    @Field("pin_code")
    private String pinCode;

    @Field("state")
    private String state;

    @Field("latitude")
    private String latitude;

    @Field("longitude")
    private String longitude;

}
