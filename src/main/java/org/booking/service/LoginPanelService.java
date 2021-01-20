package org.booking.service;

import org.booking.constant.MainMenu;
import org.booking.db.dao.UserDao;
import org.booking.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class LoginPanelService {

    private UserDao userDao;

    public LoginPanelService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String showLoginMenu() {
        return Arrays.stream(MainMenu.values())
                .filter(menu -> menu != MainMenu.UNKNOWN)
                .map(MainMenu::toString)
                .collect(Collectors.joining("\n"));
    }

    public Optional<User> getUserByUsername(String username) {
        return userDao.getByUsername(username);
    }

    public void signUp(String username, String password, String name, String surname) {
        User user = new User(0, username, password, name, surname);
        userDao.create(user);
    }
}
