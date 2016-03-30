package com.tlongdev.spicio.storage.dao;

import com.tlongdev.spicio.controller.request.UserBody;
import com.tlongdev.spicio.controller.response.UserResponse;
import com.tlongdev.spicio.controller.response.UserResponseFull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@Repository
public interface UserDao {

    UserResponse getUser(long userId);

    List<UserResponse> findUsers(String name);

    boolean deleteAllUserData(long userId);

    long addUser(UserBody userBody);

    UserResponseFull getUserFull(long userId);
}
