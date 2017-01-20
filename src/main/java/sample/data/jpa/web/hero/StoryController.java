package sample.data.jpa.web.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.data.jpa.domain.hero.Story;
import sample.data.jpa.service.hero.StorySearchCriteria;
import sample.data.jpa.service.hero.StoryService;

@RestController
@RequestMapping("/stories")
public class StoryController {

    @Autowired
    StoryService storyService;

    @RequestMapping(method = RequestMethod.GET)
    public Page get(@RequestParam(required = false) String content, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        StorySearchCriteria criteria = new StorySearchCriteria();
        if (!StringUtils.isEmpty(content)) {
            criteria.setContent(content);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<Story> storyPage = storyService.findStories(criteria, pageRequest);
        return storyPage;
    }


}
