package com.camelcase.userauthservice.service;

import com.camelcase.userauthservice.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User save(User user);
}
