package org.al.user.servicesinterface;

import org.al.user.entities.User;

public interface IJwtService {
    String generateToken(User user);
}
