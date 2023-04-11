package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// A Class that will intergrate this interface
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB; // Return the database
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst(); // Search within our DB for the person
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id).map(person1 -> { // Select and then map the person
            int indexOfPersonToUpdate = DB.indexOf(person1); // Get index
            if (indexOfPersonToUpdate >= 0) { // If Index of the person is greater or equal to 0
                DB.set(indexOfPersonToUpdate, new Person(id, update.getName())); // Set the content of that person to the person
                return 1;
            }
            return 0; // Otherwise return nothing
        }).orElse(0);
    }
}
