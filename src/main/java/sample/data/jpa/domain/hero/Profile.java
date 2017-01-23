package sample.data.jpa.domain.hero;

import javax.persistence.*;
import java.io.Serializable;


//TODO: constraint on gender
//prevents multiple values hero_profiles using the same hero_id

//added unique to joinColumn
/*@Table(uniqueConstraints = {
        @UniqueConstraint(name = "hero_profile_constraints", columnNames = {"hero_id"})
})*/
@Entity
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(length = 100000)
    private String bio;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = true)
    private String picture;

    //TODO: allows multiple values
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_id", unique = true, nullable = false)
    private Hero hero;

    public Profile() {
    }

    public Profile(String bio, Gender gender) {
        this.bio = bio;
        this.gender = gender;
    }

    public Profile(String bio, Gender gender, String picture) {
        this.bio = bio;
        this.gender = gender;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}

