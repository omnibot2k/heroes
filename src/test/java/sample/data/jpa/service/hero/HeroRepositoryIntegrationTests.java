package sample.data.jpa.service.hero;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import sample.data.jpa.domain.hero.Hero;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroRepositoryIntegrationTests {

    @Autowired
    HeroRepository repository;

    @Test
    public void createHeroes() {
        long initialCount = repository.count();
        Hero hero = new Hero();
        hero.setName("The Thing");
        Hero savedHero = repository.save(hero);
        long finalCount = repository.count();

        assertThat(savedHero.getName() == "The Thing");
        assertThat(finalCount == initialCount + 1);
        assertThat(savedHero.getId() == 6);

    }

    @Test
    public void findsAllPageOfHeroes() {
        //5 total results
        //3 pages
        Page<Hero> heroesPage1 = this.repository.findAll(new PageRequest(0, 2));
        assertThat(heroesPage1.getNumberOfElements()).isEqualTo(2);
        assertThat(heroesPage1.getTotalElements()).isEqualTo(5);

        Page<Hero> heroesPage2 = this.repository.findAll(new PageRequest(1, 2));
        assertThat(heroesPage2.getNumberOfElements()).isEqualTo(2);

        //partial
        Page<Hero> heroesPage3 = this.repository.findAll(new PageRequest(2, 2));
        assertThat(heroesPage3.getNumberOfElements()).isEqualTo(1);
    }

    @Test
    public void findsContainsPageOfHeroes() {
        //1 page contain
        final String partialName = "man";
        Page<Hero> heroesPage = this.repository.findByNameContainingAllIgnoringCase(partialName, new PageRequest(0, 5));
        assertThat(heroesPage.getNumberOfElements()).isEqualTo(2);
        List<Hero> heroes = heroesPage.getContent();
        boolean allMatch = heroes.stream()
                .allMatch(hero -> hero.getName().toLowerCase().contains(partialName));

        assertThat(allMatch);
    }

    @Test
    public void findExactMatch() {
        //1 page contain
        final String partialName = "man";
        Page<Hero> heroesPage = this.repository.findByNameContainingAllIgnoringCase(partialName, new PageRequest(0, 5));
        assertThat(heroesPage.getNumberOfElements()).isEqualTo(2);
        List<Hero> heroes = heroesPage.getContent();
        boolean allMatch = heroes.stream()
                .allMatch(hero -> hero.getName().toLowerCase().contains(partialName));

        assertThat(allMatch);
    }

    /*
    @Test
    public void findByNameIgnoringCase() {
        String name = "Blackwidow";
        Hero hero = this.repository.findByNameIgnoringCase(name);
        Profile heroProfile = hero.getProfile();
        assertThat(heroProfile.getGender() == Gender.FEMALE);
        assertThat(heroProfile.getHero().getName() == "Blackwidow");
    }*/
}
