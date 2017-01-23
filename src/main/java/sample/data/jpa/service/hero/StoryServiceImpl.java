/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.jpa.service.hero;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import sample.data.jpa.domain.hero.Story;

@Component("storyService")
@Transactional
class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;

    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public Page<Story> findStories(StorySearchCriteria criteria, Pageable pageable) {
        Assert.notNull(criteria, "Criteria must not be null");
        String content = criteria.getContent();

        Page<Story> pages = null;

        boolean noCriteria = StringUtils.isEmpty(criteria.getAuthor()) && StringUtils.isEmpty(criteria.getContent()) && StringUtils.isEmpty(criteria.getTitle());
        if (noCriteria) {
            pages = storyRepository.findAll(pageable);

            //fetch lazy heroes :)
            //actually don't fetch... this would be n+1 query...
            //seems to work now anyways
            //pages.getContent().forEach(story -> story.getHeroes().size());
        } else if (!StringUtils.isEmpty(content)) {
            pages = storyRepository.findByContentContainingAllIgnoringCase(content, pageable);
        } else {
            throw new UnsupportedOperationException("Unsupported criteria " + criteria.toString());
        }
        return pages;
    }
}
