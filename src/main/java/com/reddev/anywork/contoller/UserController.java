package com.reddev.anywork.contoller;


import com.reddev.anywork.auth.Requests;
import com.reddev.anywork.config.JwtService;
import com.reddev.anywork.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("get/{id}")
    private ResponseEntity<?> userInfo(@PathVariable UUID id) throws Exception {
        return  ResponseEntity.ok(userService.getUser(id));
    }


    @GetMapping("info")
    private ResponseEntity<?> userInfo(@RequestHeader(value = "authorization", defaultValue = "") String auth) throws Exception {
        Claims check = jwtService.verify(auth);
        return  ResponseEntity.ok(userService.userInfo(check));
    }
    @GetMapping("search")
    private ResponseEntity<?> allUsers() {
        return  ResponseEntity.ok(userService.allUser());
    }


    @DeleteMapping("delete")
    public ResponseEntity<?> deleteUser(
            @RequestHeader(value = "authorization", defaultValue = "") String auth) throws Exception {
        Claims check = jwtService.verify(auth);
        return ResponseEntity.ok(userService.deleteUser(check));
    }


    @PatchMapping("edit")
    public ResponseEntity<?> editUser(
            @RequestBody Requests requests,
            @RequestHeader(value = "authorization", defaultValue = "") String auth) throws Exception {
        Claims check = jwtService.verify(auth);
        return ResponseEntity.ok(userService.editUser(check, requests));
    }



}
