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
    public Page get(@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        HeroSearchCriteria criteria = new HeroSearchCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.setName(name);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<Hero> heroPage = heroService.findHeroes(criteria, pageRequest);

        Page<HeroDto> heroDtoPage = heroPage.map(this::heroAbilityConverter);
        return heroDtoPage;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HeroDto getWithId(@PathVariable(value = "id") Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Hero hero = heroService.getHero(id);
        return heroConverter(hero);
    }

    @RequestMapping(value = "/abilities", method = RequestMethod.GET)
    public HeroDto get(@RequestParam Long id) {
        Hero hero = heroService.findHeroAbilities(id);

        return heroAbilityConverter(hero);
    }

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
