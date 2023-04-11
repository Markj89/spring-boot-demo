package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// This is where our API will live
// The mapping request for the endpoint
@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // This is where you're getting the request body
    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    // Define the getAllPeople method
    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    // Get the ID from url path
    @GetMapping(path = "{id}") // This will allow you to have forward slash and some ID
    public Person getPersonById(@PathVariable("id") UUID id) { // Grabbing the ID and converting it into a UUID
        return personService.getPersonById(id).orElse(null);
    }

    // Since it's void, no need to return anything
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }
}
