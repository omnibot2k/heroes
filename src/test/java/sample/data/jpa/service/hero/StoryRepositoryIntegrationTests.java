package sample.data.jpa.service.hero;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import sample.data.jpa.domain.hero.Story;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoryRepositoryIntegrationTests {

    @Autowired
    StoryRepository repository;

    @Test
    public void findsAllPageOfHeroes() {
        //3 total results
        //2 pages
        Page<Story> heroesPage1 = this.repository.findAll(new PageRequest(0, 2));
        assertThat(heroesPage1.getNumberOfElements()).isEqualTo(2);
        assertThat(heroesPage1.getTotalElements()).isEqualTo(3);

        //partial
        Page<Story> heroesPage2 = this.repository.findAll(new PageRequest(1, 2));
        assertThat(heroesPage2.getNumberOfElements()).isEqualTo(1);
    }

    @Test
    public void findsContainsPageOfHeroes() {
        //1 page contain
        final String partialName = "love";
        Page<Story> heroesPage = this.repository.findByContentContainingAllIgnoringCase(partialName, new PageRequest(0, 5));
        assertThat(heroesPage.getNumberOfElements()).isEqualTo(1);
        List<Story> heroes = heroesPage.getContent();
        boolean allMatch = heroes.stream()
                .allMatch(story -> story.getContent().toLowerCase().contains(partialName));

        assertThat(allMatch);
    }
}
