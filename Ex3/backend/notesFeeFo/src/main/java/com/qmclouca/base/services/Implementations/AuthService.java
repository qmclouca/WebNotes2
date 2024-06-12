package com.qmclouca.base.services.Implementations;

import com.qmclouca.base.Dtos.AuthenticationRequest;
import com.qmclouca.base.models.Client;
import com.qmclouca.base.repositories.ClientRepository;
import com.qmclouca.base.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    @Autowired
    private ClientRepository clientRepository;

    private final String globalPassword = "your_global_password";
    public final ConcurrentHashMap<String, String> tokenStore = new ConcurrentHashMap<>();

    public String authenticate(AuthenticationRequest authRequest) throws Exception {
        Client client = clientRepository.findByClientName(authRequest.getUsername());

        if (client == null || !authRequest.getPassword().equals(client.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        String token = UUID.randomUUID().toString();
        tokenStore.put(token, client.getClientName());
        return token;
    }
    public UserDetails userDetailsbyUserName(String username) {
        Client client = clientRepository.findByClientName(username);
        if (client == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return org.springframework.security.core.userdetails.User.withUsername(client.getClientName())
                .password(client.getPassword())
                .authorities("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public boolean validateToken(String token) {
        boolean test =tokenStore.containsKey(token);

        return test;
    }

    public void invalidateToken(String token) {
        tokenStore.remove(token);
    }
}