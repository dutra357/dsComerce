package com.dutra.dscomerce.services;

import com.dutra.dscomerce.dtos.UserDto;
import com.dutra.dscomerce.entities.RoleEntity;
import com.dutra.dscomerce.entities.UserEntity;
import com.dutra.dscomerce.repositories.UserRepository;
import com.dutra.dscomerce.security.interfaces.UserDetailsProjection;
import com.dutra.dscomerce.services.interfaces.UserServiceInterface;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService, UserServiceInterface {

    private final UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> listResult = repository.searchUserAndRolesByEmail(username);

        if (listResult.size() == 0) {
            throw new UsernameNotFoundException("User not found.");
        }

        UserEntity user = new UserEntity();
        user.setEmail(username);
        user.setPassword(listResult.get(0).getPassword());

        for (UserDetailsProjection projection : listResult) {
            user.addRole(new RoleEntity(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }

    @Transactional
    public UserDto getMe() {
        UserEntity user = getAuthenticated();
        return new UserDto(user);
    }

    protected UserEntity getAuthenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            return repository.findByEmail(username).get();

        } catch (Exception exception) {
            throw new UsernameNotFoundException("Email not found.");
        }
    }
}
