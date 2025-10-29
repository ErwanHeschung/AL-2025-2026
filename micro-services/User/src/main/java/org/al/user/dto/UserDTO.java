package org.al.user.dto;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private UUID id;
    private String email;
    private String roleName;
    private String firstName;
    private String lastName;
    private String braceletId;
    private UUID doctorId;
}
