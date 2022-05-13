package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(long id, Person person) {
        final String sql = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        return jdbcTemplate.update(sql, person.getFirst_name(), person.getLast_name());
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, first_name, last_name FROM person ORDER BY id";
        return jdbcTemplate.query(sql, (resultSet, i) -> new Person(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        ));
    }

    @Override
    public Optional<Person> selectPersonById(long id) {
        final String sql = "SELECT id, first_name, last_name FROM person WHERE id = ?";
        Person person = null;

        try {
            person = jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, i) -> {
                        long personId = resultSet.getLong("id");
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        return new Person(personId, firstName, lastName);
                    },
                    id
            );
        } catch (DataAccessException dex) {
            System.out.println(dex.getMessage());
        }

        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(long id) {
        final String sql = "DELETE FROM person WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updatePersonById(long id, Person person) {
        final String sql = "UPDATE person SET first_name = ?, last_name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, person.getFirst_name(), person.getLast_name(), id);
    }

}
