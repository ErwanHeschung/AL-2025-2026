package org.al.user.servicesinterface;

import org.al.user.entities.Role;

import java.util.Optional;

public interface IRoleService {
    Role createRole(String roleName, String description);
    Optional<Role> getRoleByName(String roleName);
}
