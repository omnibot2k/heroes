package sample.data.jpa.service.hero;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sample.data.jpa.domain.hero.Ability;

interface AbilityRepository extends JpaRepository<Ability, Long> {

    Page<Ability> findAll(Pageable pageable);

    Page<Ability> findByNameContainingAllIgnoringCase(String content, Pageable pageable);
}
