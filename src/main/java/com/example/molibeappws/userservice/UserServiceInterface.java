package com.example.molibeappws.userservice;

import com.example.molibeappws.ui.model.request.UserDetailsRequestModel;
import com.example.molibeappws.ui.model.response.UserRest;

public interface UserServiceInterface {
    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);

    UserRest getUser(String userId);
}
