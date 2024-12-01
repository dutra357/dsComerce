package com.dutra.dscomerce.controllers;

import com.dutra.dscomerce.dtos.UserDto;
import com.dutra.dscomerce.services.interfaces.UserServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserServiceInterface service;

    public UserController(UserServiceInterface service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDto> getCurrentUser() {
        return ResponseEntity.ok(service.getMe());
    }

}
