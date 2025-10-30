package org.al.user.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends CustomException {
    public UserAlreadyExistsException() {
        super("User with this email already exists", HttpStatus.CONFLICT);
    }
}