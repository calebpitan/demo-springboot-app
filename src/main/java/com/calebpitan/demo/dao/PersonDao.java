package com.calebpitan.demo.dao;

import com.calebpitan.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public interface PersonDao {
    AtomicLong counter = new AtomicLong();

    int insertPerson(long id, Person person);

    default int insertPerson(Person person) {
        long id = counter.incrementAndGet();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(long id);

    int deletePersonById(long id);

    int updatePersonById(long id, Person person);
}
