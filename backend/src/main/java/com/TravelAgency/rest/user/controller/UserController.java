package com.TravelAgency.rest.user.controller;

import com.TravelAgency.rest.user.model.RegisterUserRequest;
import com.TravelAgency.rest.user.model.User;
import com.TravelAgency.rest.user.service.RegistrationService;
import com.TravelAgency.rest.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.TravelAgency.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAll() {
        var users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get-all-employers")
    public ResponseEntity<List<User>> getAllEmployers() {
        var users = userService.findAllEmployers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<User> add(@RequestBody RegisterUserRequest request) {
        var user = registrationService.registerUser(request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/lock-user")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<User> lockUser(@RequestBody RegisterUserRequest request) {
        var user =userService.lockUser(request);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/unlock-user")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<User> unlockUser(@RequestBody RegisterUserRequest request) {
        var user =userService.unlockUser(request);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/enable-user")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<HttpStatus> enableUser(@RequestBody RegisterUserRequest request) {
        var user =userService.enableUser(request.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
