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


    @RequestMapping(method = RequestMethod.POST)
    public HeroDto saveHero(@RequestBody HeroDto heroDto) {
        Hero heroToSave = convertToHero(heroDto);
        Hero savedHero = heroService.save(heroToSave);
        return convertToHeroDto(savedHero);
    }


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
            heroDtoPage = heroPage.map(this::convertToHeroDtoWithAbilities);
        } else {
            heroDtoPage = heroPage.map(this::convertToHeroDto);
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
            heroDto = convertToHeroDtoWithAbilities(hero);
        } else {
            heroDto = convertToHeroDto(hero);
        }

        return heroDto;
    }

    private Hero convertToHero(HeroDto heroDto) {
        Hero hero = new Hero();
        hero.setName(heroDto.getName());
        return hero;
    }

    //THIS MAKES A DIFFERENCE WHEN CALLING .getAbilities it loads it!!!!
    private HeroDto convertToHeroDto(Hero hero) {
        HeroDto heroDto = new HeroDto();
        heroDto.setId(hero.getId());
        heroDto.setName(hero.getName());
        return heroDto;
    }

    private HeroDto convertToHeroDtoWithAbilities(Hero hero) {
        HeroDto heroDto = new HeroDto();
        heroDto.setId(hero.getId());
        heroDto.setName(hero.getName());
        heroDto.setAbilities(hero.getAbilities());
        return heroDto;
    }


}
