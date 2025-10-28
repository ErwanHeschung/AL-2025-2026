package org.al.userrole.servicesinterface;

import org.al.userrole.entities.User;

import java.util.Optional;

public interface IUserService {
    User createUser(String username, String password);
    Optional<User> getUserByEmail(String email);
    void updateUser(User user);

}
