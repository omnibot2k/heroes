package sample.data.jpa.domain.hero;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Story implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(length = 100000)
    private String content;

    @Column(nullable = false)
    private String author; //TODO: user...

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //TODO: does updatable make sense here.. think so you can still delete
    @JoinTable(name = "story_hero", joinColumns = {
            @JoinColumn(name = "story_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "hero_id",
                    nullable = false, updatable = false)})

    private Set<Hero> heroes = new HashSet<>();

    public Story() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<Hero> heroes) {
        this.heroes = heroes;
    }
}

