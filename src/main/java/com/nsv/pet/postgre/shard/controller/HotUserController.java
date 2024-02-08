package com.nsv.pet.postgre.shard.controller;

import com.nsv.pet.postgre.shard.domain.entity.Person;
import com.nsv.pet.postgre.shard.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class HotUserController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Person person) {
        personService.saveUser(person);
        return ResponseEntity.ok("Person created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> getByUserId(@PathVariable Long id) {
        Optional<Person> users = personService.findUserById(id);
        return ResponseEntity.ok(users);
    }
}
