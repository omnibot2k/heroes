package sample.data.jpa.web.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.data.jpa.domain.hero.Hero;
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
        return heroPage;
    }


}
