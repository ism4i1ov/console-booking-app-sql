package org.booking.controller;

import org.booking.entity.User;
import org.booking.service.LoginPanelService;

import java.util.Optional;

public class LoginPanelController {

    private final LoginPanelService loginPanelService;

    public LoginPanelController(LoginPanelService loginPanelService) {
        this.loginPanelService = loginPanelService;
    }

    public String showMenu() {
        return loginPanelService.showLoginMenu();
    }

    public Optional<User> login(String username, String password) {
        return loginPanelService.getUserByUsername(username)
                .filter(user -> user.getPassword().equals(password));
    }

    public String register(String username, String password, String name, String surname) {
        return loginPanelService.getUserByUsername(username)
                .map(user -> "This username exists!")
                .orElse(signUp(username, password, name, surname));
    }

    private String signUp(String username, String password, String name, String surname) {
        loginPanelService.signUp(username, password, name, surname);
        return "Registered successfully!";
    }
}
