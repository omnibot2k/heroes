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

    @Autowired
    HeroAbilityRepository heroAbilityRepository;

    @Override
    public Page<Hero> findHeroes(HeroSearchCriteria criteria, Pageable pageable) {
        Assert.notNull(criteria, "Criteria must not be null");
        String name = criteria.getName();

        Page<Hero> pages = null;

        boolean noNameFilter = StringUtils.isEmpty(criteria.getName());
        if (noNameFilter) {
            if (criteria.getAbilities()) {
                pages = heroAbilityRepository.findAll(pageable);
            } else {
                pages = heroRepository.findAll(pageable);
            }
        } else if (!StringUtils.isEmpty(name)) {
            if (criteria.getAbilities()) {
                pages = heroAbilityRepository.findByNameContainingAllIgnoringCase(name, pageable);
            } else {
                pages = heroRepository.findByNameContainingAllIgnoringCase(name, pageable);
            }
        } else {
            throw new UnsupportedOperationException("Unsupported criteria " + criteria.toString());
        }
        return pages;
    }

    @Override
    public Hero findHero(HeroSearchCriteria criteria, Long id) {
        Assert.notNull(criteria, "Criteria must not be null");

        Hero hero = null;
        if (criteria.getAbilities()) {
            hero = heroAbilityRepository.findOne(id);
        } else {
            hero = heroRepository.findOne(id);
        }
        return hero;
    }

}
