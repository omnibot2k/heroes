package sample.data.jpa.service.hero;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import sample.data.jpa.domain.hero.Story;

interface StoryRepository extends Repository<Story, Long> {

    Page<Story> findAll(Pageable pageable);

    Page<Story> findByContentContainingAllIgnoringCase(String content, Pageable pageable);
}
