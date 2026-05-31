package com.workflow.api.service;

import com.workflow.api.dto.auth.LoginRequest;
import com.workflow.api.dto.auth.LoginResponse;
import com.workflow.api.dto.auth.RegisterRequest;
import com.workflow.api.entity.User;
import com.workflow.api.exception.auth.EmailAlreadyExistsException;
import com.workflow.api.exception.auth.InvalidCredentialsException;
import com.workflow.api.repository.UserRepository;
import com.workflow.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent())
            throw new EmailAlreadyExistsException();

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);
    }

    public LoginResponse authenticate(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            User user = userRepository.findByEmail(request.email())
                    .orElseThrow();

            return new LoginResponse(
                    jwtService.generateToken(
                            org.springframework.security.core.userdetails.User
                                    .builder()
                                    .username(user.getEmail())
                                    .password(user.getPassword())
                                    .build()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }
    }
}
