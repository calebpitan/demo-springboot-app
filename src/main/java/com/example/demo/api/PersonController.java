package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody @Valid @NotNull Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") long id) {
        return personService
                .getPersonById(id)
                .orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updatePersonById(@PathVariable("id") long id, @RequestBody @Valid @NotNull Person person) {
        personService.updatePerson(id, person);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") long id) {
        personService.deletePerson(id);
    }

}
