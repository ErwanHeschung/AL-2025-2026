package org.al.user.controllers;

import lombok.RequiredArgsConstructor;
import org.al.user.dto.UserDTO;
import org.al.user.servicesinterface.IUserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserRoleService userRoleService;

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email) {
        UserDTO  user = userRoleService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{email}/role/{roleName}")
    public ResponseEntity<String> assignRole(
            @PathVariable String email,
            @PathVariable String roleName) {

        userRoleService.assignRoleToUser(email, roleName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}/role/{roleName}")
    public ResponseEntity<Boolean> checkRole(
            @PathVariable String email,
            @PathVariable String roleName) {

        boolean hasRole = userRoleService.userHasRole(email, roleName);
        return ResponseEntity.ok(hasRole);
    }

}
