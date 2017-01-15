package sample.data.jpa.domain.hero;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Hero implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NaturalId
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_profile_id")
    private HeroProfile heroProfile;

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

    public HeroProfile getHeroProfile() {
        return heroProfile;
    }

    public void setHeroProfile(HeroProfile heroProfile) {
        this.heroProfile = heroProfile;
    }
}

