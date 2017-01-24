package sample.data.jpa.web.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.domain.hero.Hero;
import sample.data.jpa.dto.hero.HeroDto;
import sample.data.jpa.service.hero.HeroSearchCriteria;
import sample.data.jpa.service.hero.HeroService;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    HeroService heroService;

    @RequestMapping(method = RequestMethod.GET)
    public Page getHeroes(@RequestParam(required = false) String name, @RequestParam(required = false, defaultValue = "false") Boolean abilities, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        HeroSearchCriteria criteria = new HeroSearchCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.setName(name);
        }

        criteria.setAbilities(abilities);

        PageRequest pageRequest = new PageRequest(page, size);
        Page<Hero> heroPage = heroService.findHeroes(criteria, pageRequest);

        Page<HeroDto> heroDtoPage = null;
        if (criteria.getAbilities()) {
            heroDtoPage = heroPage.map(this::heroAbilityConverter);
        } else {
            heroDtoPage = heroPage.map(this::heroConverter);
        }
        return heroDtoPage;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HeroDto getHero(@PathVariable(value = "id") Long id, @RequestParam(required = false, defaultValue = "false") Boolean abilities, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        HeroSearchCriteria criteria = new HeroSearchCriteria();
        criteria.setAbilities(abilities);

        Hero hero = heroService.findHero(criteria, id);

        HeroDto heroDto = null;
        if (criteria.getAbilities()) {
            heroDto = heroAbilityConverter(hero);
        } else {
            heroDto = heroConverter(hero);
        }

        return heroDto;
    }

    //THIS MAKES A DIFFERENCE WHEN CALLING .getAbilities it loads it!!!!
    private HeroDto heroConverter(Hero hero) {
        HeroDto heroDto = new HeroDto();
        heroDto.setId(hero.getId());
        heroDto.setName(hero.getName());
        return heroDto;
    }

    private HeroDto heroAbilityConverter(Hero hero) {
        HeroDto heroDto = new HeroDto();
        heroDto.setId(hero.getId());
        heroDto.setName(hero.getName());
        heroDto.setAbilities(hero.getAbilities());
        return heroDto;
    }


}
