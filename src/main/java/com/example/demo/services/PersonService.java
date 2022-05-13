package com.example.demo.services;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
         return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(long id) {
        return personDao.selectPersonById(id);
    }

    public int updatePerson(long id, Person person) {
        return personDao.updatePersonById(id, person);
    }

    public int deletePerson(long id) {
        return personDao.deletePersonById(id);
    }

}
