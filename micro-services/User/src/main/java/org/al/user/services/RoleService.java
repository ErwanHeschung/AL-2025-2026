package org.al.user.services;

import lombok.RequiredArgsConstructor;
import org.al.user.entities.Role;
import org.al.user.exceptions.RoleNotFoundException;
import org.al.user.repositories.RoleRepository;
import org.al.user.servicesinterface.IRoleService;
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