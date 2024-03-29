package com.nsv.pet.postgre.shard.service;

import com.nsv.pet.postgre.shard.domain.entity.Person;
import com.nsv.pet.postgre.shard.repository.ColdPersonPersonRepository;
import com.nsv.pet.postgre.shard.repository.HotPersonPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MigrationService {
    public static final int ACTUAL_VERSION = 0;

    @Autowired
    private HotPersonPersonRepository hotPersonRepository;

    @Autowired
    private ColdPersonPersonRepository coldPersonRepository;

    /*
     * Eсли "version" больше ACTUAL_VERSION, то считаем эту запись устаревшей
     */
    private void migrateData() {
        List<Person> persons = hotPersonRepository.findAll();
        List<Person> oldPersons = persons.stream().filter(person -> person.getVersion() > ACTUAL_VERSION).toList();

        for (Person oldPerson : oldPersons) {
            coldPersonRepository.create(oldPerson);
            hotPersonRepository.deleteById(oldPerson.getUuid());
        }
    }
    @Transactional
    public void migrateOldData() {
        migrateData();
    }
    
    @Transactional
    @Async
    public void migrateOldDataAsync() {
        migrateData();
    }
}
