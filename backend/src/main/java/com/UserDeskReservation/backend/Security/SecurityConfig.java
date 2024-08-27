package com.UserDeskReservation.backend.Security;

import com.UserDeskReservation.backend.Service.Implementation.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Lazy
    private EmployeeService employeeService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/companies/**").permitAll()
                                .requestMatchers("/api/departments/**").permitAll()
                                .requestMatchers("/api/desks/**").permitAll()
                                .requestMatchers("/api/employees/register").permitAll()
                                .requestMatchers("/api/employees/update/**").permitAll()
                                .requestMatchers("/api/employees/delete/**").permitAll()
                                .requestMatchers("/api/employees/employee-list").permitAll()
                                .requestMatchers("/api/reservations/history/**").permitAll()
                                .requestMatchers("/api/employees/changePassword/**").permitAll()
                                .requestMatchers("/api/reservations/reserve").permitAll()
                                .requestMatchers("/api/reservations/update/**").permitAll()
                                .requestMatchers("/api/reservations/delete/**").permitAll()
                                .requestMatchers("/api/employees/login").permitAll()
                                .requestMatchers("/api/employees/forgotPassword").permitAll()
                                .requestMatchers("/api/employees/search/**").permitAll()
                                .requestMatchers("/api/employees/**").permitAll()

                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint((request, response, authException) -> {
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                                })
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().flush();
                        })
                        .permitAll()
                );
        return http.build();
    }
}