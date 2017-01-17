package sample.data.jpa.service.hero;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import sample.data.jpa.domain.hero.Hero;

interface HeroRepository extends Repository<Hero, Long> {

    Page<Hero> findAll(Pageable pageable);

    Page<Hero> findByNameContainingAllIgnoringCase(String name, Pageable pageable);

    Hero findByNameIgnoringCase(String name);

}
