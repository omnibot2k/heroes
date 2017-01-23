package sample.data.jpa.domain.hero;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedEntityGraphs({

        @NamedEntityGraph(name = "hero.simple", attributeNodes = {
                //doesn't seem to matter???
                //@NamedAttributeNode("id")
        }),
        @NamedEntityGraph(name = "hero.abilities", attributeNodes = {
                @NamedAttributeNode("abilities")
        })
})
public class Hero implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NaturalId
    private String name;

    //http://stackoverflow.com/questions/30464782/how-to-maintain-bi-directional-relationships-with-spring-data-rest-and-jpa
    //read response .. i probably don't want bi-directional here...
    //hmm one to one always fetches eagerly this is a known issue!!!
    /*
    @JsonIgnore
    @OneToOne(optional = false, fetch = FetchType.LAZY, mappedBy = "hero", cascade = CascadeType.REMOVE)
    private Profile profile;
*/

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "heroes", cascade = CascadeType.REMOVE)
    private Set<Story> stories = new HashSet<>();

    //omg jackson is causing this to be loaded... NOT SPRING DATA IGNORING FETCHTYPE!!!!!
    //argg testing name graphs !!!@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "heroes", cascade = CascadeType.REMOVE)
    private Set<Ability> abilities = new HashSet<>();

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

    /*
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }*/

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

