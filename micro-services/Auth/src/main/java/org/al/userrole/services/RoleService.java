package org.al.userrole.services;

import lombok.RequiredArgsConstructor;
import org.al.userrole.entities.Role;
import org.al.userrole.exceptions.RoleNotFoundException;
import org.al.userrole.repositories.RoleRepository;
import org.al.userrole.servicesinterface.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public Role createRole(String roleName, String description) {
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RoleNotFoundException(roleName));

        if (role == null) {
            role = Role.builder()
                    .name(roleName)
                    .description(description)
                    .build();
            roleRepository.save(role);
        }
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> getRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}