package sample.data.jpa.dto.hero;

import sample.data.jpa.domain.hero.Ability;
import sample.data.jpa.domain.hero.Story;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class HeroDto implements Serializable {

    private Long id;
    private String name;
    private Set<Story> stories = new HashSet<>();
    private Set<Ability> abilities = new HashSet<>();

    public HeroDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Story> getStories() {
        return stories;
    }

    public void setStories(Set<Story> stories) {
        this.stories = stories;
    }

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }
}

