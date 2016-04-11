package com.tlongdev.spicio.controller;

import com.tlongdev.spicio.controller.request.UserBody;
import com.tlongdev.spicio.controller.response.UserResponse;
import com.tlongdev.spicio.controller.response.UserResponseFull;
import com.tlongdev.spicio.storage.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired private UserDao userDao;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> searchUser(@RequestParam("query") String query) {
        if (query == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(userDao.findUsers(query));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@Valid @RequestBody UserBody user) {
        //At least one of them shouldn't be null
        if (user.getFacebookId() == null && user.getGoogleId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        long id = userDao.addUser(user);

        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(locationUri).body(null);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable long userId, @RequestParam(value = "full", defaultValue = "false") String full) {
        if (full.equals("true")) {
            UserResponseFull user = userDao.getUserFull(userId);
            return ResponseEntity.ok(user);
        } else {
            UserResponse user = userDao.getUser(userId);
            return ResponseEntity.ok(user);
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        userDao.deleteAllUserData(userId);
        return ResponseEntity.ok(null);
    }
}
