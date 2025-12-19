package com.xcell.GTManager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures application-wide security settings.
 * <p>
 * Defines authentication requirements, public endpoints (for resource access), login behavior,
 * session persistence and user identity management.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Endpoints that are publicly accessible without authentication.
     */
    private final String[] AUTH_WHITELIST = {
            "/login",
            "/css/**",
            "/favicon.png"
    };

    /**
     * Configures the HTTP security filter chain.
     * <p>
     * All requests require authentication except those specifically whitelisted in {@link #AUTH_WHITELIST}.
     * Authentication is handled via a custom login page with form-based login.
     * Remember-me support is enabled to persist authentication across browser sessions.
     *
     * @param http the HTTP security configuration
     * @return the configured security filter chain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .rememberMe(remember -> remember
                        .key("A_VERY_LONG_AND_RANDOM_SECRET_KEY")
                        .tokenValiditySeconds(60 * 60 * 24 * 14) // 14 days
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                );
        return http.build();
    }

    /**
     * Provides the password encoder to use for hashing passwords.
     * @return a BCrypt password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the user details service for authentication.
     * <p>
     * Uses an in-memory store with a single user named "admin" with password "admin"
     * intended for development purposes only.
     *
     * @param encoder the password encoder used to hash stored passwords
     * @return the configured user details service
     */
    @Bean
    public UserDetailsService users(PasswordEncoder encoder) {
        UserDetails user = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
