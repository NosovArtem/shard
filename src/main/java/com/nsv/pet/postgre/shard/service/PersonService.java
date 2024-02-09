package com.nsv.pet.postgre.shard.service;

import com.nsv.pet.postgre.shard.domain.entity.Person;
import com.nsv.pet.postgre.shard.repository.ColdPersonPersonRepository;
import com.nsv.pet.postgre.shard.repository.HotPersonPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final ShardService shardService;

    private final MigrationService migrationService;


    public void saveUser(Person person) {
        String uuid = String.valueOf(UUID.randomUUID());
        person.setUuid(uuid);
        shardService.create(person);
    }

    public Optional<Person> findUserById(String userId) {
        return shardService.findById(userId);
    }

    /*
    * Логика для чтения из холодного хранилища (при необходимости)
    */
    public Optional<Person> findColdUserId(String userId) {
        return shardService.findById(userId);
    }
}
