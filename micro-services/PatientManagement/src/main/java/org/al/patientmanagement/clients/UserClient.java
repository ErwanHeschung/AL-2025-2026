package org.al.patientmanagement.clients;

import org.al.patientmanagement.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {

    @GetMapping("/users/{id}")
    PatientDTO getUserById(@PathVariable("id") UUID userId);

    @GetMapping("/users/by-doctor")
    List<PatientDTO> getUsersByDoctorId(
            @RequestParam("doctorId") UUID doctorId
    );
}