package com.semvalidator.repository;

import com.semvalidator.conf.AppWebConfiguration;
import com.semvalidator.conf.JPAConfiguration;
import com.semvalidator.model.Checklist;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={AppWebConfiguration.class, JPAConfiguration.class})
@ActiveProfiles("dev")
public class ChecklistRepositoryIT {

    @Autowired
    ChecklistRepository checklistRepository;

    @Test
    public void testFindByQuestion(){
        List<Checklist> checklists = checklistRepository.findByQuestion(20);
        MatcherAssert.assertThat(checklists, Matchers.hasSize(4));
    }
}
