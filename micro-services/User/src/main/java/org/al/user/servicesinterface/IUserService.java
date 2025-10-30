package org.al.user.servicesinterface;

import org.al.user.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    User createUser(User user);
    Optional<User> getUserByEmail(String email);
    void updateUser(User user);
    Optional<User> getUserById(UUID userId);
    List<User> getUserByDoctorId(UUID doctorId);
}
