package com.tlongdev.spicio.controller;

import com.tlongdev.spicio.converter.UserConverter;
import com.tlongdev.spicio.controller.request.UserBody;
import com.tlongdev.spicio.storage.dao.SequenceDao;
import com.tlongdev.spicio.storage.dao.UserDao;
import com.tlongdev.spicio.storage.document.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired private UserDao userDao;
    @Autowired private SequenceDao sequenceDao;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable long userId, @RequestParam(value = "full", defaultValue = "false") String full) {
        UserDocument user = userDao.getUser(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (full.equals("true")) {
            return ResponseEntity.ok(UserConverter.convertToUserResponseFull(user));
        } else {
            return ResponseEntity.ok(UserConverter.convertToUserResponse(user));
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserBody user) {
        if (user.getFacebookId() != null) {
            UserDocument result = userDao.getUserByFacebookId(user.getFacebookId());
            if (result == null) {
                result = UserConverter.convertToUserDocument(user);
                result.setId(sequenceDao.nextValue("user"));
                result = userDao.saveUser(result);
            }
            return mapToResponseEntity(result);
        } else if (user.getGoogleId() != null) {
            UserDocument result = userDao.getUserByGoogleId(user.getGoogleId());
            if (result == null) {
                result = UserConverter.convertToUserDocument(user);
                result.setId(sequenceDao.nextValue("user"));
                result = userDao.saveUser(result);
            }
            return mapToResponseEntity(result);
        }
        return ResponseEntity.badRequest().body(null);
    }

    private ResponseEntity<?> mapToResponseEntity(UserDocument user) {
        URI locationUri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(locationUri).body(null);
    }
}
