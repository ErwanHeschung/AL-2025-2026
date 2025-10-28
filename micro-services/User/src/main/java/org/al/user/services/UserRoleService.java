package org.al.user.services;

import org.al.user.exceptions.RoleNotFoundException;
import org.al.user.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.al.user.dto.RoleDTO;
import org.al.user.dto.UserDTO;
import org.al.user.entities.Role;
import org.al.user.entities.User;
import org.al.user.servicesinterface.IRoleService;
import org.al.user.servicesinterface.IUserRoleService;
import org.al.user.servicesinterface.IUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService implements IUserRoleService {

    private final IUserService userService;
    private final IRoleService roleService;

    @Override
    public void assignRoleToUser(String email, String roleName) {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        Role role = roleService.getRoleByName(roleName).
                orElseThrow(() -> new RoleNotFoundException(roleName));
        if (user != null && role != null) {
            user.setRole(role);
            userService.updateUser(user);
        }
    }

    @Override
    public boolean userHasRole(String email, String roleName) {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        return user != null && user.getRole() != null &&
                user.getRole().getName().equals(roleName);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        return userToUserDTO(user);
    }

    private UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(RoleDTO.builder()
                        .id(user.getRole().getId())
                        .name(user.getRole().getName())
                        .build())
                .build();
    }
}
