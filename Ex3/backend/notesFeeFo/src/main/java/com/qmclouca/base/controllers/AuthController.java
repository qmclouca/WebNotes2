package com.qmclouca.base.controllers;

import com.qmclouca.base.Dtos.AuthenticationRequest;
import com.qmclouca.base.Dtos.AuthenticationResponse;
import com.qmclouca.base.models.Client;
import com.qmclouca.base.services.ClientService;
import com.qmclouca.base.services.Implementations.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    ClientService clientService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authRequest) {
        try {
            String token = authService.authenticate(authRequest);
            Optional<Client> clientData = clientService.getClientByName(authRequest.getUsername());
            AuthenticationResponse response = null;
            if (clientData.isPresent()) {
                Client client = clientData.get();
                response = new AuthenticationResponse(token, client.getId());
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}