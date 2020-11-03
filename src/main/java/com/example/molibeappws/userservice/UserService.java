package com.example.molibeappws.userservice;

import com.example.molibeappws.shared.Utils;
import com.example.molibeappws.ui.model.request.UserDetailsRequestModel;
import com.example.molibeappws.ui.model.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserServiceInterface {

    Map<String, UserRest> users;
    Utils utils;

    @Autowired
    public UserService(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        String userId = utils.generateUserId();
        UserRest returnValue = UserRest.builder()
                .userId(userId)
                .email(userDetails.getEmail())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .build();

        if (users == null) {
            users = new HashMap<>(); //Collections.emptyMap();
        }
        users.put(userId, returnValue);

        return returnValue;
    }

    @Override
    public UserRest getUser(String userId) {

        if (users.containsKey(userId)) {
            return users.get(userId);
        }

        return null;
    }
}
