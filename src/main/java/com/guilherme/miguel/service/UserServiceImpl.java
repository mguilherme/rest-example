package com.guilherme.miguel.service;

import com.guilherme.miguel.domain.User;
import com.guilherme.miguel.domain.User.Contact;
import com.guilherme.miguel.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * User Service Impl
 *
 * @author Miguel Guilherme
 */
@Service
public class UserServiceImpl implements UserService {

    public User getUser(Long id) {
        if (id != 20) {
            throw new UserNotFoundException(format("User %s not found", id), id);
        }

        // In a production Application this should be retrieved from a database
        return new User(id, "Miguel", new Contact("Lisbon", "999999999"));
    }
}
