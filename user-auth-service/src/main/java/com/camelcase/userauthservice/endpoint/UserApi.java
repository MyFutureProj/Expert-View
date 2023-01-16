package com.camelcase.userauthservice.endpoint;

import com.camelcase.userauthservice.model.User;
import com.camelcase.userauthservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api/user")
public class UserApi {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public boolean createUser(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user = userRepository.save(user);
        return Objects.nonNull(user) ? true : false;
    }
}
