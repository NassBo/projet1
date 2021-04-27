package fr.nb.zyma.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link fr.nb.zyma.domain.ApplicationUser} entity.
 */
@ApiModel(description = "ApplicationUser entity.\n@author Boubziz Nassim.")
public class ApplicationUserDTO implements Serializable {

    private Long id;

    private String profilBanner;

    private String profilBio;

    private UserDTO internalUser;

    private Set<ApplicationUserDTO> friends = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfilBanner() {
        return profilBanner;
    }

    public void setProfilBanner(String profilBanner) {
        this.profilBanner = profilBanner;
    }

    public String getProfilBio() {
        return profilBio;
    }

    public void setProfilBio(String profilBio) {
        this.profilBio = profilBio;
    }

    public UserDTO getInternalUser() {
        return internalUser;
    }

    public void setInternalUser(UserDTO internalUser) {
        this.internalUser = internalUser;
    }

    public Set<ApplicationUserDTO> getFriends() {
        return friends;
    }

    public void setFriends(Set<ApplicationUserDTO> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUserDTO)) {
            return false;
        }

        ApplicationUserDTO applicationUserDTO = (ApplicationUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, applicationUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUserDTO{" +
            "id=" + getId() +
            ", profilBanner='" + getProfilBanner() + "'" +
            ", profilBio='" + getProfilBio() + "'" +
            ", internalUser=" + getInternalUser() +
            ", friends=" + getFriends() +
            "}";
    }
}
