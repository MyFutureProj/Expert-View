package com.camelcase.userauthservice.endpoint;

import com.camelcase.userauthservice.response.MessageResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.camelcase.userauthservice.constants.UserServiceConstant.API;
import static com.camelcase.userauthservice.constants.UserServiceConstant.USER;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(API + USER)
public class UserController {

    @GetMapping("/all")
    public MessageResponse allAccess() {
        return new MessageResponse("Server is up.....");
    }

    @GetMapping("/greeting")
    @PreAuthorize("isAuthenticated()")
    public MessageResponse userAccess() {
        return new MessageResponse("Congratulations! You are an authenticated user.");
    }

}
