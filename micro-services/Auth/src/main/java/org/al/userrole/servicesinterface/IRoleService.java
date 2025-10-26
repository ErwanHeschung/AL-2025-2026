package org.al.userrole.servicesinterface;

import org.al.userrole.entities.Role;

import java.util.Optional;

public interface IRoleService {
    Role createRole(String roleName, String description);
    Optional<Role> getRoleByName(String roleName);
}
