package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Person {
    private final long id;

    @NotBlank
    private final String first_name;

    @NotBlank
    private final String last_name;

    public Person(
            @JsonProperty("id") long id,
            @JsonProperty("first_name") String fName,
            @JsonProperty("last_name") String lName
    ) {
        this.id = id;
        this.first_name = fName;
        this.last_name = lName;
    }

    public long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}
