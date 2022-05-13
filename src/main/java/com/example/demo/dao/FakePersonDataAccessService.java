package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static final List<Person> db = new ArrayList<>();

    @Override
    public int insertPerson(long id, Person person) {
        db.add(new Person(id, person.getFirst_name(), person.getLast_name()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return db;
    }

    @Override
    public Optional<Person> selectPersonById(long id) {
        return db.stream()
                .filter(person -> (person.getId() == id))
                .findFirst();
    }

    @Override
    public int deletePersonById(long id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isPresent()) {
            db.remove(personMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(long id, Person person) {
        return selectPersonById(id)
                .map(p -> {
                    int indexOfPersonToUpdate = db.indexOf(p);
                    if (indexOfPersonToUpdate >= 0) {
                        db.set(indexOfPersonToUpdate,
                                new Person(id, person.getFirst_name(),
                                        person.getLast_name()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
