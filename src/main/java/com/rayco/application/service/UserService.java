package com.rayco.application.service;

import com.rayco.application.client.KeycloakClient;
import com.rayco.application.exceptions.NotFoundException;
import com.rayco.domain.entity.User;
import com.rayco.domain.repository.UserRepository;
import com.rayco.presentation.dto.KeycloakUserDTO;
import com.rayco.presentation.dto.LoginDTO;
import com.rayco.presentation.dto.UserCreateDTO;
import com.rayco.presentation.dto.UserInfoDTO;
import com.rayco.presentation.mapper.KeycloakUserMapper;
import com.rayco.presentation.mapper.UserMapper;
import com.rayco.presentation.response.TokenResponse;
import com.rayco.presentation.response.UserLoggedResponse;
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
    private final TokenService tokenService;

    public UserService(UserRepository repository, UserMapper mapper, KeycloakUserMapper keycloakUserMapper, KeycloakClient keycloakClient, TokenService tokenService) {
        this.repository = repository;
        this.mapper = mapper;
        this.keycloakUserMapper = keycloakUserMapper;
        this.keycloakClient = keycloakClient;
        this.tokenService = tokenService;
        this.restTemplate = new RestTemplate();
    }


    public UserLoggedResponse login(LoginDTO dto){
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

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

        return mapper.mapUserLoggedResponse(user, response.getBody());
    }

    @Transactional
    public UserLoggedResponse register(@Valid UserCreateDTO dto) {
        User userToSave = mapper.mapEntityForRegister(dto);
        User createdUser = repository.save(userToSave);
        String accessToken = getAdminUserAccessToken();
        KeycloakUserDTO keycloakUserDTO = keycloakUserMapper.toKeycloakUserDTO(createdUser, dto.getPassword());

        try {
            keycloakClient.createUser(realm, accessToken, keycloakUserDTO);

        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw e;
        }

        return buildUserCreatedDTO(createdUser, dto.getPassword());
    }

    private String getAdminUserAccessToken(){
        LoginDTO loginDTO = LoginDTO.builder().email(email).password(password).build();
        TokenResponse tokenResponse = login(loginDTO).getToken();
        return String.format("Bearer %s",  tokenResponse.getAccessToken());
    }

    private UserLoggedResponse buildUserCreatedDTO(User user, String password){
        LoginDTO loginDTO = LoginDTO.builder().email(user.getEmail()).password(password).build();
        TokenResponse tokenResponse = login(loginDTO).getToken();
        return mapper.mapUserLoggedResponse(user, tokenResponse);
    }

    public User getLoggedUser(){
        String email = tokenService.getCurrentUserEmail();
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void saveUserInfo(UserInfoDTO dto) {
        User user = mapper.toEntity(dto);
        repository.save(user);
    }
}
