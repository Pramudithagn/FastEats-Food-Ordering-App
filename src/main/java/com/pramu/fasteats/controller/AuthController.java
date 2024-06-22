package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.Cart;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.repository.CartRepository;
import com.pramu.fasteats.repository.UserRepository;
import com.pramu.fasteats.request.LoginRequest;
import com.pramu.fasteats.response.AuthResponse;
import com.pramu.fasteats.service.JwtService;
import com.pramu.fasteats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {
        User userExist = userRepository.findByEmail(user.getEmail());
        if(userExist != null){
            throw new Exception("Email used already");
        }

        User registerUser = new User();
        registerUser.setEmail(user.getEmail());
        registerUser.setPassword(passwordEncoder.encode(user.getPassword()));
        registerUser.setFullName(user.getFullName());
        registerUser.setRole(user.getRole());

        User savedUser = userRepository.save(registerUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.buildToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest request) throws Exception {

    }
}
