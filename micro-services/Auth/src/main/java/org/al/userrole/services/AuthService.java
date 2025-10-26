package org.al.userrole.services;

import org.al.userrole.exceptions.InvalidCredentialsException;
import org.al.userrole.exceptions.RoleNotFoundException;
import org.al.userrole.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.al.userrole.entities.Role;
import org.al.userrole.entities.User;
import org.al.userrole.servicesinterface.IAuthService;
import org.al.userrole.servicesinterface.IJwtService;
import org.al.userrole.servicesinterface.IRoleService;
import org.al.userrole.servicesinterface.IUserService;
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
