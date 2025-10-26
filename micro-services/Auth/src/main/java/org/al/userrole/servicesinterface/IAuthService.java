package org.al.userrole.servicesinterface;

public interface IAuthService {
    void createUserWithRole(String username, String password, String roleName);
    String login(String username, String password);
}
