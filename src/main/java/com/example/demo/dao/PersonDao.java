package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Interface is where we define the operations allowed for the actual contract for anyone that wishes to implement this interface.
public interface PersonDao {
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    // This is where we're return our list from the database
    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(UUID id);

    // Delete
    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);
}
