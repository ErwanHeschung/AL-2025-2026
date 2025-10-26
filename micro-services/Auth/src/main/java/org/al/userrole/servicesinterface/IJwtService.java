package org.al.userrole.servicesinterface;

import org.al.userrole.entities.User;

public interface IJwtService {
    String generateToken(User user);
}
