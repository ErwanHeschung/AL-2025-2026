package org.al.user.servicesinterface;

import org.al.user.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserRoleService {

    void assignRoleToUser(String email, String roleName);

    boolean userHasRole(String email, String roleName);

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(UUID userId);

    List<UserDTO> getUsersByDoctorId(UUID doctorId);
}