package com.nsv.pet.postgre.shard.service;

import com.nsv.pet.postgre.shard.domain.entity.Person;
import com.nsv.pet.postgre.shard.repository.ColdPersonRepository;
import com.nsv.pet.postgre.shard.repository.HotPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private HotPersonRepository hotPersonRepository;

    @Autowired
    private ColdPersonRepository coldPersonRepository;

    @Autowired
    private MigrationService migrationService;

    public void saveUser(Person person) {
        hotPersonRepository.create(person);
    }

    public Optional<Person> findUserById(Long userId) {
        return hotPersonRepository.findById(userId);
    }

    /*
    * Логика для чтения из холодного хранилища (при необходимости)
    */
    public Optional<Person> findColdUserId(Long userId) {
        return coldPersonRepository.findById(userId);
    }
}
