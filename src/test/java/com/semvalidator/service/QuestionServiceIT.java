package com.semvalidator.service;


import com.semvalidator.conf.DataSourceConfigurationTest;
import com.semvalidator.conf.JPAConfiguration;
import com.semvalidator.repository.QuestionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class, QuestionRepository.class,
        DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class QuestionServiceIT {

    @Autowired
    private QuestionService questionService;


    @Test
    public void testService(){
        Assert.assertNotNull(questionService);
    }
}
