package com.tlongdev.spicio.controller;

import com.tlongdev.spicio.controller.response.UserResponse;
import com.tlongdev.spicio.storage.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author longi
 * @since 2016.04.10.
 */
@RestController
@RequestMapping("/api/v1/users/{userId}/friends")
public class FriendController {

    @Autowired private UserDao userDao;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> getFriends(@PathVariable long userId) {
        return ResponseEntity.ok(userDao.findFriends(userId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addFriend(@PathVariable long userId, @RequestParam("friend_id") long friendId) {
        userDao.addFriend(userId, friendId);
        return ResponseEntity.ok(null);
    }
}
