package org.al.user.controllers;

import lombok.RequiredArgsConstructor;
import org.al.user.dto.UserDTO;
import org.al.user.servicesinterface.IUserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserRoleService userRoleService;

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

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") UUID userId) {
        return userRoleService.getUserById(userId);
    }

    @GetMapping("/by-doctor")
    public List<UserDTO> getUsersByDoctorId(@RequestParam("doctorId") UUID doctorId) {
        return userRoleService.getUsersByDoctorId(doctorId);
    }
}
