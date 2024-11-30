package com.dutra.dscomerce.services;

import com.dutra.dscomerce.entities.Role;
import com.dutra.dscomerce.entities.User;
import com.dutra.dscomerce.repositories.UserRepository;
import com.dutra.dscomerce.security.interfaces.UserDetailsProjection;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

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

        User user = new User();
        user.setEmail(username);
        user.setPassword(listResult.get(0).getPassword());

        for (UserDetailsProjection projection : listResult) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }
}
