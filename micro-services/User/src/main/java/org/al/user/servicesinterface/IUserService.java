package org.al.user.servicesinterface;

import org.al.user.entities.User;

import java.util.Optional;

public interface IUserService {
    User createUser(String username, String password);
    Optional<User> getUserByEmail(String email);
    void updateUser(User user);

}
