package de.g_griffin.recipe_manager_backend.user_info;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class UserInfo {
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
