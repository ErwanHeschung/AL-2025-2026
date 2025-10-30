package org.al.user.services;

import org.al.user.dto.CreateUserRequest;
import org.al.user.exceptions.InvalidCredentialsException;
import org.al.user.exceptions.RoleNotFoundException;
import org.al.user.exceptions.UserAlreadyExistsException;
import org.al.user.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.al.user.entities.Role;
import org.al.user.entities.User;
import org.al.user.servicesinterface.IAuthService;
import org.al.user.servicesinterface.IJwtService;
import org.al.user.servicesinterface.IRoleService;
import org.al.user.servicesinterface.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserService userService;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        if (userService.getUserByEmail(createUserRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User user = User.builder()
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .braceletId(createUserRequest.getBraceletId())
                .build();
        user = userService.createUser(user);
        Role role = roleService.getRoleByName(createUserRequest.getRoleName()).
                orElseThrow(() -> new RoleNotFoundException(createUserRequest.getRoleName()));
        if (role == null) throw new RoleNotFoundException(createUserRequest.getRoleName());
        user.setRole(role);
        userService.updateUser(user);
    }

    @Override
    public String login(String email, String password) {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));


        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return jwtService.generateToken(user);
    }
}
