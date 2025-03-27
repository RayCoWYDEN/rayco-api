package com.rayco.application.service;

import com.rayco.application.client.KeycloakClient;
import com.rayco.domain.entity.User;
import com.rayco.domain.repository.UserRepository;
import com.rayco.presentation.dto.KeycloakUserDTO;
import com.rayco.presentation.dto.LoginDTO;
import com.rayco.presentation.dto.UserCreateDTO;
import com.rayco.presentation.mapper.KeycloakUserMapper;
import com.rayco.presentation.mapper.UserMapper;
import com.rayco.presentation.response.TokenResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserService {
    @Value("${keycloak.url}")
    private String url;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.grant-type}")
    private String grantType;

    @Value("${keycloak.admin.credentials.email}")
    private String email;

    @Value("${keycloak.admin.credentials.password}")
    private String password;

    private final RestTemplate restTemplate;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final KeycloakUserMapper keycloakUserMapper;
    private final KeycloakClient keycloakClient;

    public UserService(UserRepository repository, UserMapper mapper, KeycloakUserMapper keycloakUserMapper, KeycloakClient keycloakClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.keycloakUserMapper = keycloakUserMapper;
        this.keycloakClient = keycloakClient;
        this.restTemplate = new RestTemplate();
    }


    public TokenResponse login(LoginDTO dto){
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", grantType);
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("username", dto.getEmail());
        map.add("password", dto.getPassword());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        String endpoint = String.format("%s/realms/%s/protocol/openid-connect/token", url, realm);

        ResponseEntity<TokenResponse> response = restTemplate.exchange(
                endpoint, HttpMethod.POST, entity, TokenResponse.class);

        return response.getBody();
    }

    @Transactional
    public TokenResponse register(@Valid UserCreateDTO dto) {
        User userToSave = mapper.mapEntityForRegister(dto);
        User createdUser = repository.save(userToSave);
        String accessToken = getAdminUserAccessToken();

        KeycloakUserDTO keycloakUserDTO = keycloakUserMapper.toKeycloakUserDTO(createdUser, dto.getPassword());

        keycloakClient.createUser(realm, accessToken, keycloakUserDTO);

        return getCreatedUserToken(dto);
    }

    private String getAdminUserAccessToken(){
        LoginDTO loginDTO = LoginDTO.builder().email(email).password(password).build();
        TokenResponse tokenResponse = login(loginDTO);
        return String.format("Bearer %s",  tokenResponse.getAccessToken());
    }

    private TokenResponse getCreatedUserToken(UserCreateDTO user){
        LoginDTO loginDTO = LoginDTO.builder().email(user.getEmail()).password(user.getPassword()).build();
        return login(loginDTO);
    }

}
