package com.TravelAgency.security.user.controller;

import com.TravelAgency.registration.RegistrationRequest;
import com.TravelAgency.registration.RegistrationService;
import com.TravelAgency.security.TokenProvider;
import com.TravelAgency.security.user.model.AuthResponse;
import com.TravelAgency.security.user.model.LoginUser;
import com.TravelAgency.security.user.model.User;
import com.TravelAgency.security.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/")
public class LoginController {
    private final RegistrationService registrationService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;


    @PostMapping("/login")
    public AuthResponse loginUser(@Valid @RequestBody LoginUser user) {
        String token = authenticateAndGetToken(user.getEmail(), user.getPassword());
        return new AuthResponse(token);
    }


    @PostMapping("/register")
    public HttpStatus register(@RequestBody RegistrationRequest request) {
        registrationService.register(request);
        return HttpStatus.OK;
    }

    public String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generate(authentication);
    }
}
