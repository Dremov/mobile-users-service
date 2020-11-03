package com.example.molibeappws.ui.controller;

import com.example.molibeappws.exceptions.UserServiceException;
import com.example.molibeappws.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.molibeappws.ui.model.request.UserDetailsRequestModel;
import com.example.molibeappws.ui.model.response.UserRest;
import com.example.molibeappws.userservice.UserService;
import com.example.molibeappws.userservice.UserServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    Map<String, UserRest> users;

    final UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(
            @RequestParam(value="page", defaultValue="1") int page,
            @RequestParam(value="limit", defaultValue="50") int limit,
            @RequestParam(value="sort", defaultValue="desc", required = false) String sort) {
        logger.info("get Users was called with page = " + page + " and limit = " + limit);
        return "get Users was called with page = " + page + " and limit = " + limit;
    }

    @GetMapping(path="/{userId}")
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        logger.info("get User was called with userId = " + userId);
        var returnedUser = userService.getUser(userId);

        if (returnedUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(returnedUser);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

        var returnValue = userService.createUser(userDetails);
        logger.info("created User - " + returnValue.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PutMapping(path="/{userId}")
    public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetails) {
        var storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(updateUserDetails.getFirstName());
        storedUserDetails.setLastName(updateUserDetails.getLastName());

        users.put(userId, storedUserDetails);

        logger.info("update User - " + storedUserDetails.toString());

        return storedUserDetails;
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<UserRest> deleteUser(@PathVariable String userId) {
        logger.info("delete User" + users.get(userId).toString());
        users.remove(userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
