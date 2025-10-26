package org.al.userrole.servicesinterface;

import org.al.userrole.dto.UserDTO;

public interface IUserRoleService {

    void assignRoleToUser(String email, String roleName);

    boolean userHasRole(String email, String roleName);

    UserDTO getUserByEmail(String email);
}