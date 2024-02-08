package com.nsv.pet.postgre.shard.controller;

import com.nsv.pet.postgre.shard.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/migration")
public class MigrationController {

    @Autowired
    private MigrationService migrationService;

    @GetMapping("/run")
    public void runMigration() {
        migrationService.migrateOldData();
    }
}
