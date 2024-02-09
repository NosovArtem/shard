package com.nsv.pet.postgre.shard.service;

import com.nsv.pet.postgre.shard.domain.entity.Person;
import com.nsv.pet.postgre.shard.repository.ColdPersonPersonRepository;
import com.nsv.pet.postgre.shard.repository.HotPersonPersonRepository;
import com.nsv.pet.postgre.shard.repository.ShardPersonRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//  RendezvousCache
@Service
public class ShardService implements ShardPersonRepository {
    private Map<String, ShardPersonRepository> rendezvousMap;

    private final ColdPersonPersonRepository coldPersonPersonRepository;
    private final HotPersonPersonRepository hotPersonPersonRepository;


    public ShardService(ColdPersonPersonRepository coldPersonPersonRepository, HotPersonPersonRepository hotPersonPersonRepository) {
        this.rendezvousMap = new HashMap<>();
        this.coldPersonPersonRepository = coldPersonPersonRepository;
        this.hotPersonPersonRepository = hotPersonPersonRepository;
        rendezvousMap.put(coldPersonPersonRepository.getClass().getSimpleName(), coldPersonPersonRepository);
        rendezvousMap.put(hotPersonPersonRepository.getClass().getSimpleName(), hotPersonPersonRepository);
    }

    // Метод для определения узла данных
    public String determineNode(String key) {
        int maxHashCode = Integer.MIN_VALUE;
        String selectedNode = null;

        for (String node : rendezvousMap.keySet()) {
            int hashCode = (key + node).hashCode();
            if (hashCode > maxHashCode) {
                maxHashCode = hashCode;
                selectedNode = node;
            }
        }

        return selectedNode;
    }

    private String getKey(Person value) {
        return value.getUuid();
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public Optional<Person> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void create(Person person) {
        String selectedNode = determineNode(getKey(person));
        ShardPersonRepository shardPersonRepository = rendezvousMap.get(selectedNode);
        shardPersonRepository.create(person);
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void deleteById(String id) {

    }
}
