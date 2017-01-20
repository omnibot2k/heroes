package sample.data.jpa.service.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import sample.data.jpa.domain.hero.Hero;

import javax.transaction.Transactional;

@Transactional
@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    HeroRepository heroRepository;

    @Override
    public Page<Hero> findHeroes(HeroSearchCriteria criteria, Pageable pageable) {
        Assert.notNull(criteria, "Criteria must not be null");
        String name = criteria.getName();

        Page<Hero> pages = null;

        boolean noCriteria = StringUtils.isEmpty(criteria.getName());
        if (noCriteria) {
            pages = heroRepository.findAll(pageable);
        } else if (!StringUtils.isEmpty(name)) {
            pages = heroRepository.findByNameContainingAllIgnoringCase(name, pageable);
        } else {
            throw new UnsupportedOperationException("Unsupported criteria " + criteria.toString());
        }
        return pages;
    }

    @Override
    public Hero getHero(String name, Pageable pageable) {
        return heroRepository.findByNameIgnoringCase(name);
    }
}
