package com.example.molibeappws.ui.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRest {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}
