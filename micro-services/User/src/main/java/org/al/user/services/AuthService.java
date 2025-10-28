package org.al.user.services;

import org.al.user.exceptions.InvalidCredentialsException;
import org.al.user.exceptions.RoleNotFoundException;
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
    public void createUserWithRole(String email, String password, String roleName) {
        User user = userService.createUser(email, password);
        Role role = roleService.getRoleByName(roleName).
                orElseThrow(() -> new RoleNotFoundException(roleName));
        if (role == null) throw new RoleNotFoundException(roleName);
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
