package org.al.user.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String email) {
        super("User not found: " + email, HttpStatus.NOT_FOUND);
    }
}