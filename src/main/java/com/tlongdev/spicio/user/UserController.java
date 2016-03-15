package com.tlongdev.spicio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserDao userDao;

    @RequestMapping("/{userId}")
    public User getUser(@PathVariable long userId) {
        User user = userDao.getUser(userId);
        if (user == null) {
            User notFoundUser = new User();
            notFoundUser.setId(userId);
            return notFoundUser;
        }
        return user;
    }
}
