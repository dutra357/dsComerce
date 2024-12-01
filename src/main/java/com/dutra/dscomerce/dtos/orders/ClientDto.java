package com.dutra.dscomerce.dtos.orders;

import com.dutra.dscomerce.entities.UserEntity;

public class ClientDto {

    private Long id;
    private String name;

    public ClientDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClientDto(UserEntity entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
