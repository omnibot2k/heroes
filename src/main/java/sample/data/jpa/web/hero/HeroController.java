package sample.data.jpa.web.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sample.data.jpa.service.hero.HeroService;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    HeroService heroService;

    @Autowired
    UserResourceAssembler assembler;


    @RequestMapping(method = RequestMethod.GET)
    Pageab readBookmarks(@PathVariable String userId) {
        this.validateUser(userId);
        return this.bookmarkRepository.findByAccountUsername(userId);
    }



}
