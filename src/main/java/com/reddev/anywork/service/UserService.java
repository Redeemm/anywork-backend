package com.reddev.anywork.service;


import com.reddev.anywork.auth.AuthenticationResponse;
import com.reddev.anywork.auth.Requests;
import com.reddev.anywork.exception.HttpHandler.NotAcceptableException;
import com.reddev.anywork.exception.HttpHandler.Response;
import com.reddev.anywork.model.user.User;
import com.reddev.anywork.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> allUser () {
        List<User> user = userRepository.findAllUsers();
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> getUser (UUID id) throws NotAcceptableException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotAcceptableException("User do not exist!!"));
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> userInfo (Claims check) throws NotAcceptableException {
        User user = userRepository.findByEmail(check.getSubject())
                .orElseThrow(() -> new NotAcceptableException("User do not exist!!"));
        return ResponseEntity.ok(user);
    }

    public Response deleteUser(Claims check) {
        UUID userId = userRepository.findByEmail(check.getSubject()).get().getUserId();

        userRepository.deleteById(userId);
        return new Response(
                HttpStatus.OK,
                "User Deleted Successfully!!",
                LocalDateTime.now());
    }

    public ResponseEntity<?> editUser(Claims check, Requests request) {

        User user = userRepository.findByEmail(check.getSubject())
                .orElseThrow(() -> new RuntimeException("User do not exist"));

        var updateUser = user.toBuilder()
                .role(request.getRole())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .enabled(true)
                .locked(false)
                .build();

        userRepository.save(updateUser);
        return ResponseEntity.ok(user);
    }
}
