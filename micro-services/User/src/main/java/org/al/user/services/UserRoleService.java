package org.al.user.services;

import org.al.user.exceptions.RoleNotFoundException;
import org.al.user.exceptions.UserIdNotFoundException;
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

import java.util.List;
import java.util.UUID;

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

    @Override
    public UserDTO getUserById(UUID userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new UserIdNotFoundException(userId));

        return userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getUsersByDoctorId(UUID doctorId) {
        List<User> users = userService.getUserByDoctorId(doctorId);

        return users.stream()
                .map(this::userToUserDTO)
                .toList();
    }

    private UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roleName(user.getRole() != null ? user.getRole().getName() : null)
                .braceletId(user.getBraceletId())
                .doctorId(user.getDoctorId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
