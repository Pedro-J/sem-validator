package com.semvalidator.repository;


import com.semvalidator.conf.AppWebConfiguration;
import com.semvalidator.conf.JPAConfiguration;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={AppWebConfiguration.class, JPAConfiguration.class})
@ActiveProfiles("test")
public class QuestionRepositoryIT {

    @Autowired
    QuestionRepository questionRepository;


    @Test
    public void testFindAll(){
        Assert.assertNotNull(questionRepository);
        assertThat(questionRepository.findAll().size(), Matchers.greaterThan(50));
    }
}
