package com.nsv.pet.postgre.shard;

import com.nsv.pet.postgre.shard.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledMigrationTask {

    @Autowired
    private MigrationService migrationService;

    @Scheduled(cron = "0 0 2 * * *") // Запуск в 2 часа ночи каждый день
    public void runMigration() {
        migrationService.migrateOldDataAsync();
    }
}
