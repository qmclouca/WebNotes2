package com.qmclouca.base.repositories;

import com.qmclouca.base.models.User;
import org.springframework.stereotype.Repository;

public interface UserRepository {
    User findByUsername(String username);
}
