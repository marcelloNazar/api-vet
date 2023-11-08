package vet.center.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.center.api.config.AuthService;
import vet.center.api.domain.animal.Animal;
import vet.center.api.user.AuthRequest;
import vet.center.api.user.AuthResponse;
import vet.center.api.user.RegisterRequest;
import vet.center.api.user.User;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> alter(@PathVariable Long id, @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.updateUser(id, request));
    }
}