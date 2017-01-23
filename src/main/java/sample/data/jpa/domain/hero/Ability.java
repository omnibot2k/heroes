package sample.data.jpa.domain.hero;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ability implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer cost;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AbilityType type;

    @Column
    private Integer points;

    //need to ignore or recursive loop...
    //http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    //@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //TODO: does updatable make sense here.. think so you can still delete
    @JoinTable(name = "hero_ability", joinColumns = {
            @JoinColumn(name = "ability_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "hero_id",
                    nullable = false, updatable = false)})
    private Set<Hero> heroes = new HashSet<>();

    public Ability() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public AbilityType getType() {
        return type;
    }

    public void setType(AbilityType type) {
        this.type = type;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Set<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<Hero> heroes) {
        this.heroes = heroes;
    }
}

