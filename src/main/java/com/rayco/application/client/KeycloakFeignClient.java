package com.rayco.application.client;

import com.rayco.presentation.dto.KeycloakUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "keycloakClient", url = "{keycloak.server.url}")
interface KeycloakFeignClient {

    @PostMapping("/admin/realms/{realm}/users")
    void createUser(
        @PathVariable("realm")  String realm,
        @RequestHeader("Authorization") String token,
        @RequestBody KeycloakUserDTO user
    );



}


