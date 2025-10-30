package org.al.user.exceptions;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class UserIdNotFoundException extends CustomException {
    public UserIdNotFoundException(UUID userId) {
        super("User not found: " + userId, HttpStatus.NOT_FOUND);
    }
}