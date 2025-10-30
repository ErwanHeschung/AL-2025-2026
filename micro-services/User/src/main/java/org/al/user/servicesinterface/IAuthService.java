package org.al.user.servicesinterface;

import org.al.user.dto.CreateUserRequest;

public interface IAuthService {
    void createUser(CreateUserRequest createUserRequest);
    String login(String username, String password);
}
