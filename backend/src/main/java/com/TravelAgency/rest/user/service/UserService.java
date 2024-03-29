package com.TravelAgency.rest.user.service;

import com.TravelAgency.rest.user.model.*;
import com.TravelAgency.rest.user.registration.token.ConfirmationToken;
import com.TravelAgency.rest.user.registration.token.ConfirmationTokenService;
import com.TravelAgency.rest.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.TravelAgency.security.TokenProvider.*;

@Service
public class UserService implements UserDetailsService {
    public final static String USER_NOT_FOUND = "Failed to find user with email ";
    private final static String USER_ID_NOT_FOUND = "Failed to find user with id ";
    private final static String EMAIL_IS_TAKEN = "Email is already taken ";
    private final static String CANT_LOGIN = "Email or password its incorrect";
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration.minutes}")
    private Long jwtExpirationMinutes;

    @Autowired
    public UserService(UserRepository repository, ConfirmationTokenService confirmationTokenService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = repository;
        this.confirmationTokenService = confirmationTokenService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND + email));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new FindException(USER_ID_NOT_FOUND + id));
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new FindException(USER_NOT_FOUND + email));
    }

    public String signUpUser(User newUser, boolean setEnabled) {
        boolean userExist =
                userRepository.findUserByEmail(newUser.getEmail()).isPresent();

        if (userExist) {
            throw new IllegalStateException(EMAIL_IS_TAKEN);
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(newUser.getPassword());

        newUser.setPassword(encodedPassword);
        newUser.setEnabled(setEnabled);

        userRepository.save(newUser);

        String token = generate(newUser);
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                newUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll().stream().filter(it -> it.getUserRole() == UserRole.User).toList();
    }

    public List<User> findAllEmployers() {
        return userRepository.findAll().stream().filter(it -> it.getUserRole() == UserRole.Employee).toList();
    }

    public User loginUser(LoginUser loginUser) {
        if (loginUser == null || loginUser.getEmail().isEmpty()) {
            throw new FindException(USER_NOT_FOUND);
        }
        User user = userRepository.findUserByEmail(loginUser.getEmail())
                .orElseThrow(() -> new FindException(USER_NOT_FOUND + loginUser.getEmail()));

        if (bCryptPasswordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
            return user;
        } else
            throw new IllegalStateException(CANT_LOGIN);
    }

    public boolean isUserEnabled(String email) {
        return userRepository.findUserByEmail(email).map(User::getEnabled).orElseThrow(() -> new FindException(USER_NOT_FOUND + email));
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    public User lockUser(RegisterUserRequest request) {
        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(FindException::new);

        user.setLocked(true);
        return userRepository.save(user);
    }

    public User unlockUser(RegisterUserRequest request) {
        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(FindException::new);

        user.setLocked(false);
        return userRepository.save(user);
    }


    public void changePassword(ChangePasswordRequest request) {
        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(() -> new FindException(USER_NOT_FOUND));

        if (bCryptPasswordEncoder.matches(request.getLastPassword(), user.getPassword())) {
            if (bCryptPasswordEncoder.matches(request.getNewPassword(), user.getPassword())) {
                throw new IllegalStateException("Cannot change password to this same!");
            }
            user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new IllegalStateException("Last password its incorrect");
        }
    }

    public String generate(User user) {
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        byte[] signingKey = jwtSecret.getBytes();

        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMinutes).toInstant()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setId(UUID.randomUUID().toString())
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .claim("rol", roles)
                .claim("name", user.getFirstName() + " " + user.getLastName())
                .claim("preferred_username", user.getUsername())
                .claim("email", user.getEmail())
                .compact();
    }
}
