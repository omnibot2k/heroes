/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.data.jpa.service.hero;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import sample.data.jpa.domain.hero.Gender;
import sample.data.jpa.domain.hero.Hero;
import sample.data.jpa.domain.hero.Profile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link HeroRepository}.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroRepositoryIntegrationTests {

    @Autowired
    HeroRepository repository;

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

    @Test
    public void findByNameIgnoringCase() {
        String name = "Blackwidow";
        Hero hero = this.repository.findByNameIgnoringCase(name);
        Profile heroProfile = hero.getHeroProfile();
        assertThat(heroProfile.getGender() == Gender.FEMALE);
        assertThat(heroProfile.getHero().getName() == "Blackwidow");
    }
}
