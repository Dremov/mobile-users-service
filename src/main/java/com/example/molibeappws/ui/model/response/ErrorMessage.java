package com.example.molibeappws.ui.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorMessage {
    private final Date timestamp;
    private final String message;
}
