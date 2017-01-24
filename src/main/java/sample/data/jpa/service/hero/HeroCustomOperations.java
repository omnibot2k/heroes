package sample.data.jpa.service.hero;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import sample.data.jpa.domain.hero.Hero;

public interface HeroCustomOperations {
    Page<Hero> findAll(Pageable pageable);

    Page<Hero> getAll(Pageable pageable);

    Page<Hero> findByNameContainingAllIgnoringCase(String name, Pageable pageable);

    Page<Hero> getByNameContainingAllIgnoringCase(String name, Pageable pageable);

    Hero findOne(@Param("id") long id);

    Hero getOne(@Param("id") long id);
}
