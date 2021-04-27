package fr.nb.zyma.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ApplicationUser entity.\n@author Boubziz Nassim.
 */
@Entity
@Table(name = "application_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "profil_banner")
    private String profilBanner;

    @Column(name = "profil_bio")
    private String profilBio;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private User internalUser;

    @OneToMany(mappedBy = "applicationUser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tags", "applicationUser" }, allowSetters = true)
    private Set<Article> articles = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "rel_application_user__friends",
        joinColumns = @JoinColumn(name = "application_user_id"),
        inverseJoinColumns = @JoinColumn(name = "friends_id")
    )
    @JsonIgnoreProperties(value = { "internalUser", "articles", "friends", "followers" }, allowSetters = true)
    private Set<ApplicationUser> friends = new HashSet<>();

    @ManyToMany(mappedBy = "friends")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "internalUser", "articles", "friends", "followers" }, allowSetters = true)
    private Set<ApplicationUser> followers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser id(Long id) {
        this.id = id;
        return this;
    }

    public String getProfilBanner() {
        return this.profilBanner;
    }

    public ApplicationUser profilBanner(String profilBanner) {
        this.profilBanner = profilBanner;
        return this;
    }

    public void setProfilBanner(String profilBanner) {
        this.profilBanner = profilBanner;
    }

    public String getProfilBio() {
        return this.profilBio;
    }

    public ApplicationUser profilBio(String profilBio) {
        this.profilBio = profilBio;
        return this;
    }

    public void setProfilBio(String profilBio) {
        this.profilBio = profilBio;
    }

    public User getInternalUser() {
        return this.internalUser;
    }

    public ApplicationUser internalUser(User user) {
        this.setInternalUser(user);
        return this;
    }

    public void setInternalUser(User user) {
        this.internalUser = user;
    }

    public Set<Article> getArticles() {
        return this.articles;
    }

    public ApplicationUser articles(Set<Article> articles) {
        this.setArticles(articles);
        return this;
    }

    public ApplicationUser addArticle(Article article) {
        this.articles.add(article);
        article.setApplicationUser(this);
        return this;
    }

    public ApplicationUser removeArticle(Article article) {
        this.articles.remove(article);
        article.setApplicationUser(null);
        return this;
    }

    public void setArticles(Set<Article> articles) {
        if (this.articles != null) {
            this.articles.forEach(i -> i.setApplicationUser(null));
        }
        if (articles != null) {
            articles.forEach(i -> i.setApplicationUser(this));
        }
        this.articles = articles;
    }

    public Set<ApplicationUser> getFriends() {
        return this.friends;
    }

    public ApplicationUser friends(Set<ApplicationUser> applicationUsers) {
        this.setFriends(applicationUsers);
        return this;
    }

    public ApplicationUser addFriends(ApplicationUser applicationUser) {
        this.friends.add(applicationUser);
        applicationUser.getFollowers().add(this);
        return this;
    }

    public ApplicationUser removeFriends(ApplicationUser applicationUser) {
        this.friends.remove(applicationUser);
        applicationUser.getFollowers().remove(this);
        return this;
    }

    public void setFriends(Set<ApplicationUser> applicationUsers) {
        this.friends = applicationUsers;
    }

    public Set<ApplicationUser> getFollowers() {
        return this.followers;
    }

    public ApplicationUser followers(Set<ApplicationUser> applicationUsers) {
        this.setFollowers(applicationUsers);
        return this;
    }

    public ApplicationUser addFollowers(ApplicationUser applicationUser) {
        this.followers.add(applicationUser);
        applicationUser.getFriends().add(this);
        return this;
    }

    public ApplicationUser removeFollowers(ApplicationUser applicationUser) {
        this.followers.remove(applicationUser);
        applicationUser.getFriends().remove(this);
        return this;
    }

    public void setFollowers(Set<ApplicationUser> applicationUsers) {
        if (this.followers != null) {
            this.followers.forEach(i -> i.removeFriends(this));
        }
        if (applicationUsers != null) {
            applicationUsers.forEach(i -> i.addFriends(this));
        }
        this.followers = applicationUsers;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUser)) {
            return false;
        }
        return id != null && id.equals(((ApplicationUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUser{" +
            "id=" + getId() +
            ", profilBanner='" + getProfilBanner() + "'" +
            ", profilBio='" + getProfilBio() + "'" +
            "}";
    }
}
