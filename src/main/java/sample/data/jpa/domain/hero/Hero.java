package sample.data.jpa.domain.hero;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Hero implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NaturalId
    private String name;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "hero")
    private Profile heroProfile;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "hero")
    private Set<Story> stories = new HashSet<>();

    public Hero() {
    }

    public Hero(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getHeroProfile() {
        return heroProfile;
    }

    public void setHeroProfile(Profile heroProfile) {
        this.heroProfile = heroProfile;
    }

    public Set<Story> getStories() {
        return stories;
    }

    public void setStories(Set<Story> stories) {
        this.stories = stories;
    }
}

