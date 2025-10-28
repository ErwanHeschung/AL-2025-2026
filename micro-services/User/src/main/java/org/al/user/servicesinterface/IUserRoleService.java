package org.al.user.servicesinterface;

import org.al.user.dto.UserDTO;

public interface IUserRoleService {

    void assignRoleToUser(String email, String roleName);

    boolean userHasRole(String email, String roleName);

    UserDTO getUserByEmail(String email);
}