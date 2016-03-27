package com.tlongdev.spicio.controller;

import com.tlongdev.spicio.controller.request.UserBody;
import com.tlongdev.spicio.controller.response.UserResponse;
import com.tlongdev.spicio.converter.UserConverter;
import com.tlongdev.spicio.storage.dao.SequenceDao;
import com.tlongdev.spicio.storage.dao.UserDao;
import com.tlongdev.spicio.storage.document.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired private UserDao userDao;
    @Autowired private SequenceDao sequenceDao;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> searchUser(@RequestParam("query") String query) {
        if (query == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<UserResponse> users = new LinkedList<>();

        for (UserDocument userDoc : userDao.findUsers(query)) {
            users.add(UserConverter.convertToUserResponse(userDoc));
        }

        return ResponseEntity.ok(users);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@Valid @RequestBody UserBody user) {
        if (user.getFacebookId() == null && user.getGoogleId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        UserDocument result;
        if (user.getFacebookId() != null) {
            result = userDao.getUserByFacebookId(user.getFacebookId());
        } else {
            result = userDao.getUserByGoogleId(user.getGoogleId());
        }

        if (result == null) {
            result = UserConverter.convertToUserDocument(user);
            result.setId(sequenceDao.nextValue("user"));
            result.buildSearchTerm();
            result = userDao.saveUser(result);
        }

        URI locationUri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(locationUri).body(null);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable long userId, @RequestParam(value = "full", defaultValue = "false") String full) {
        UserDocument user = userDao.getUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (full.equals("true")) {
            return ResponseEntity.ok(UserConverter.convertToUserResponseFull(user));
        } else {
            return ResponseEntity.ok(UserConverter.convertToUserResponse(user));
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable long userId) {
        if (userDao.deleteAllUserData(userId)) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
