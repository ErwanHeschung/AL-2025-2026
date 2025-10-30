package org.al.patientmanagement.clients;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "process-data-service", url = "${process.data.service.url}")
public interface ProcessDataClient {
    //TODO
}