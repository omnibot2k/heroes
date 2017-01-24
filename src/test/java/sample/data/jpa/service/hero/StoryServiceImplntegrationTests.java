package sample.data.jpa.service.hero;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import sample.data.jpa.domain.hero.Hero;
import sample.data.jpa.domain.hero.Story;

import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoryServiceImplntegrationTests {

    @Autowired
    private
    StoryService storyService;

    @Test
    public void findsAllPageOfHeroes() {
        StorySearchCriteria criteria = new StorySearchCriteria();
        //3 total results
        //2 pages
        Page<Story> heroesPage1 = this.storyService.findStories(criteria, new PageRequest(0, 2));
        assertThat(heroesPage1.getNumberOfElements()).isEqualTo(2);
        assertThat(heroesPage1.getTotalElements()).isEqualTo(3);

        //partial
        Page<Story> heroesPage2 = this.storyService.findStories(criteria, new PageRequest(1, 2));
        assertThat(heroesPage2.getNumberOfElements()).isEqualTo(1);

        //not eager fetching anymore...
        //boolean exectedHeroes = heroesPage2.getContent().stream().allMatch(story -> story.getHeroes().stream().allMatch(isHeroHulkOrBlackwidow));
        //Assert.assertEquals("Hulk and Blackwidow are contained in heroes", true, exectedHeroes);
    }

    private static Predicate<Hero> isHeroHulkOrBlackwidow = hero -> hero.getName().equals("Hulk") || hero.getName().equals("Blackwidow");

    @Test
    public void findsContainsPageOfHeroes() {
        //1 page contain
        final String partialName = "love";
        StorySearchCriteria criteria = new StorySearchCriteria();
        criteria.setContent(partialName);

        Page<Story> heroesPage = this.storyService.findStories(criteria, new PageRequest(0, 5));
        assertThat(heroesPage.getNumberOfElements()).isEqualTo(1);
        List<Story> heroes = heroesPage.getContent();
        boolean allMatch = heroes.stream()
                .allMatch(story -> story.getContent().toLowerCase().contains(partialName));

        assertThat(allMatch);
    }
}
