package de.g_griffin.recipe_manager_backend.user_info;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.g_griffin.recipe_manager_backend.exceptions.ResourceNotFoundException;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class UserInfoService {

    private static final String USER_INFO_ENDPOINT = "http://localhost:8180/realms/SpringBootKeycloak/protocol/openid-connect/userinfo";
    private final RestTemplate restTemplate;

    @Autowired
    public UserInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchSubFromUserInfoEndpoint(String bearerToken) throws JsonProcessingException {
        String accessToken = bearerToken.split(" ")[1];
        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    USER_INFO_ENDPOINT,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            HttpStatus statusCode = (HttpStatus) response.getStatusCode();

            if (statusCode.is2xxSuccessful()) {
                UserInfo userInfo = new ObjectMapper().readValue(response.getBody(), UserInfo.class);
                return userInfo.sub;
            } else {
                throw new HttpClientErrorException(statusCode, "HTTP request failed");
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceNotFoundException("Resource not found");
            } else {
                throw ex;
            }
        }
    }

    @Getter
    private static class UserInfo {
        @Id
        private String sub;

        @JsonProperty("email_verified")
        private boolean emailVerified;

        private String name;

        @JsonProperty("preferred_username")
        private String preferredUsername;

        @JsonProperty("given_name")
        private String givenName;

        @JsonProperty("family_name")
        private String familyName;

        private String email;
    }
}
