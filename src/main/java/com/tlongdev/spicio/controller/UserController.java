package com.tlongdev.spicio.controller;

import com.tlongdev.spicio.domain.User;
import com.tlongdev.spicio.storage.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserDao userDao;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable long userId) {
        User user = userDao.getUser(userId);
        if (user == null) {
            User notFoundUser = new User();
            notFoundUser.setId(userId);
            return notFoundUser;
        }
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user) {

        if (user.getFacebookId() != null) {
            User result = userDao.getUserByFacebookId(user.getFacebookId());
            if (result == null) {
                result = userDao.saveUser(user);
            }
            return mapToResponseEntity(result);
        } else if (user.getGoogleId() != null) {
            User result = userDao.getUserByGoogleId(user.getGoogleId());
            if (result == null) {
                result = userDao.saveUser(user);
            }
            return mapToResponseEntity(result);
        }
        return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> mapToResponseEntity(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }
}
