package sample.data.jpa.service.hero;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import sample.data.jpa.domain.hero.Hero;

interface HeroAbilityRepository extends Repository<Hero, Long> {

    @EntityGraph(value = "hero.abilities", type = EntityGraph.EntityGraphType.FETCH)
        //@Query(value = "SELECT distinct h, a FROM Hero h LEFT JOIN FETCH h.abilities as a",
        //        countName = "SELECT distinct h from Hero")
    Page<Hero> findAll(Pageable pageable);

    @EntityGraph(value = "hero.abilities", type = EntityGraph.EntityGraphType.FETCH)
    Page<Hero> findByNameContainingAllIgnoringCase(String name, Pageable pageable);

    @EntityGraph(value = "hero.abilities", type = EntityGraph.EntityGraphType.FETCH)
        //@Query(value = "SELECT h, a FROM Hero h LEFT JOIN FETCH h.abilities as a where h.id = :id")
    Hero findOne(@Param("id") long id);
}
