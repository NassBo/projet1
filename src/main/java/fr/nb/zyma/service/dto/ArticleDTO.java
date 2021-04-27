package fr.nb.zyma.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link fr.nb.zyma.domain.Article} entity.
 */
@ApiModel(description = "Article entity.\n@author Boubziz Nassim.")
public class ArticleDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer userId;

    @NotNull
    private String title;

    @NotNull
    private String articleBody;

    @NotNull
    private LocalDate dateArticle;

    private Set<TagDTO> tags = new HashSet<>();

    private ApplicationUserDTO applicationUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    public LocalDate getDateArticle() {
        return dateArticle;
    }

    public void setDateArticle(LocalDate dateArticle) {
        this.dateArticle = dateArticle;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    public ApplicationUserDTO getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUserDTO applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleDTO)) {
            return false;
        }

        ArticleDTO articleDTO = (ArticleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, articleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", title='" + getTitle() + "'" +
            ", articleBody='" + getArticleBody() + "'" +
            ", dateArticle='" + getDateArticle() + "'" +
            ", tags=" + getTags() +
            ", applicationUser=" + getApplicationUser() +
            "}";
    }
}
