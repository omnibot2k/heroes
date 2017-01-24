package sample.data.jpa.service.hero;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sample.data.jpa.domain.hero.Hero;

interface HeroRepository extends CrudRepository<Hero, Long> {/*}, HeroCustomOperations {*/

    @EntityGraph(value = "hero.simple", type = EntityGraph.EntityGraphType.FETCH)
    Page<Hero> findAll(Pageable pageable);

    @EntityGraph(value = "hero.simple", type = EntityGraph.EntityGraphType.FETCH)
    Page<Hero> findByNameContainingAllIgnoringCase(String name, Pageable pageable);

    @EntityGraph(value = "hero.simple", type = EntityGraph.EntityGraphType.FETCH)
    Hero findOne(@Param("id") long id);
}
