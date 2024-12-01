package com.dutra.dscomerce.dtos.orders;

import com.dutra.dscomerce.entities.UserEntity;

public class ClientDto {

    private Long id;
    private String name;

    public ClientDto() {}
    public ClientDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClientDto(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
