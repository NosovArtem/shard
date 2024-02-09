package com.nsv.pet.postgre.shard.repository;


import com.nsv.pet.postgre.shard.domain.entity.Person;

import java.util.List;
import java.util.Optional;

public interface ShardPersonRepository {
    public List<Person> findAll();

    public Optional<Person> findById(String id);

    public void create(Person person);

    public void update(Person person);

    public void deleteById(String id);
}
