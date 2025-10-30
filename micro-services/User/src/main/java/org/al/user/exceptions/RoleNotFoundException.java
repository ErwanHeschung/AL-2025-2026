package org.al.user.exceptions;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends CustomException {
    public RoleNotFoundException(String roleName) {
        super("Role not found: " + roleName, HttpStatus.NOT_FOUND);
    }
}