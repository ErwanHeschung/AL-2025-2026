package org.al.userrole.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String email) {
        super("User not found: " + email, HttpStatus.NOT_FOUND);
    }
}