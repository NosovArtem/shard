package com.nsv.pet.postgre.shard.repository;

import com.nsv.pet.postgre.shard.domain.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HotPersonPersonRepository implements ShardPersonRepository {

    @Autowired
    @Qualifier("hotJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_SQL = "SELECT * FROM persons";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM cold_users WHERE id = ?";
    private static final String INSERT_SQL = "INSERT INTO persons (id, version, username) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE persons SET username = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM persons WHERE id = ?";


    public List<Person> findAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> findById(String id) {
        Person person = jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
        return Optional.ofNullable(person);
    }

    public void create(Person person) {
        jdbcTemplate.update(INSERT_SQL, person.getUuid(), person.getVersion(), person.getUsername());
    }

    public void update(Person person) {
        jdbcTemplate.update(UPDATE_SQL, person.getUsername(), person.getUuid());
    }

    public void deleteById(String id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }
}
