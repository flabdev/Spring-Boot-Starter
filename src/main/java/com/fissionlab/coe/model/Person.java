package com.fissionlab.coe.model;

import lombok.*;

import java.util.UUID;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private UUID id;
    private String firstName;
    private String lastName;
}

