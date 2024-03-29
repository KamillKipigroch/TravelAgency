package com.TravelAgency.security.config;


import com.TravelAgency.rest.user.model.UserRole;
import com.TravelAgency.security.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    public static final String ADMIN = UserRole.Admin.toString();
    public static final String USER = UserRole.User.toString();

    private static final String[] APPLICATION_WHITE_LIST = {
            "/public/**", "/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/error",
            "/",
            "/csrf",
            "/find/**",
            "/api/auth/**"
    };

    private static final String[] ADMINISTRATOR_WHITE_LIST = {
            "/add",
            "/update",
            "/delete"
    };

    private static final String[] USER_AND_ADMINISTRATOR_WHITE_LIST = {
            "/add-object",
            "/update-object",
            "/delete-object",
            "/get-all-objects",
            "/find-object/**"
    };

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(USER_AND_ADMINISTRATOR_WHITE_LIST).hasAnyAuthority(USER, ADMIN)
                .antMatchers(ADMINISTRATOR_WHITE_LIST).permitAll()
                .antMatchers(APPLICATION_WHITE_LIST).permitAll()
                .anyRequest().permitAll();
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable();
        return http.build();
    }

}
