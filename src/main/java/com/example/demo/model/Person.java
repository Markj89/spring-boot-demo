package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

// This is how we build the api. Think of it similar to a JS Class where we're adding out variables to the constructor.
public class Person {
    private final UUID id;
    @NotBlank // If we have an empty string, it's not null
    private final String name;

    // This is where we're defining our properties
    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
