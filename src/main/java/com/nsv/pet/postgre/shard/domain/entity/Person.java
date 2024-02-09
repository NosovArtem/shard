package com.nsv.pet.postgre.shard.domain.entity;


public class Person {

    private String uuid;

    private Long version;

    private String username;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long userId) {
        this.version = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
