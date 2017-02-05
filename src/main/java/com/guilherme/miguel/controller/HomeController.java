package com.guilherme.miguel.controller;

import com.guilherme.miguel.domain.User;
import com.guilherme.miguel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home Controller
 *
 * @author Miguel Guilherme
 */
@Slf4j
@RestController
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "user/{id}")
    public User home(@PathVariable Long id) {
        log.info("Retrieving user {}", id);
        return userService.getUser(id);
    }

}
