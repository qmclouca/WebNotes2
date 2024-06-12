package com.qmclouca.base.Dtos;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class AuthTemp {
    private String token;
    private UserDetails userDetails;

}
