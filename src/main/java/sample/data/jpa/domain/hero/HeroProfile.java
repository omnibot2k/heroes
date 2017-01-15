package sample.data.jpa.domain.hero;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class HeroProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(length = 100000)
    private String bio;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(nullable = true)
    private String picture;

    @OneToOne(mappedBy = "heroProfile")
    private Hero hero;

    public HeroProfile() {
    }

    public HeroProfile(String bio, Gender gender) {
        this.bio = bio;
        this.gender = gender;
    }

    public HeroProfile(String bio, Gender gender, String picture) {
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

