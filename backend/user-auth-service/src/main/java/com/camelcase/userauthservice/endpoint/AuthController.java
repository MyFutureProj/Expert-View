package com.camelcase.userauthservice.endpoint;

import com.camelcase.userauthservice.dto.LoginRequestDTO;
import com.camelcase.userauthservice.dto.SignupRequestDTO;
import com.camelcase.userauthservice.jwt.JwtTokenUtil;
import com.camelcase.userauthservice.model.User;
import com.camelcase.userauthservice.response.JwtResponse;
import com.camelcase.userauthservice.response.MessageResponse;
import com.camelcase.userauthservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.camelcase.userauthservice.constants.UserServiceConstant.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(API + AUTH)
public class AuthController {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping(LOGIN)
    public ResponseEntity<?> login(@RequestBody @Valid final LoginRequestDTO request) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            return ResponseEntity.ok(new JwtResponse(user.getUsername(), user.getEmail(), TOKEN_TYPE, accessToken));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
    }

    @PostMapping(SIGNUP)
    public ResponseEntity<?> signUp(@RequestBody @Valid final SignupRequestDTO request) {
        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username is already taken!"));
        }
        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = User.builder().username(request.getUsername()).email(request.getEmail()).password(encoder.encode(request.getPassword())).build();
        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}