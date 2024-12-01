package com.dutra.dscomerce.services;

import com.dutra.dscomerce.entities.UserEntity;
import com.dutra.dscomerce.services.exceptions.ForbidenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void validationSelfOrAdmin(Long userId) {
        UserEntity user = userService.getAuthenticated();

        if (!user.hasRole("ROLE_ADMIN") && !user.getId().equals(userId)) {
            throw new ForbidenException("Pedido de outro cliente.");
        }
    }
}
