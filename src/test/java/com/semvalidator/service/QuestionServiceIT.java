package com.semvalidator.service;


import com.semvalidator.conf.AppWebConfiguration;
import com.semvalidator.conf.JPAConfiguration;
import com.semvalidator.repository.QuestionRepository;
import com.semvalidator.service.impl.QuestionServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={AppWebConfiguration.class, JPAConfiguration.class})
@ActiveProfiles("test")
public class QuestionServiceIT {

    @Autowired
    QuestionService questionService;


    @Test
    public void testService(){
        Assert.assertNotNull(questionService);
    }
}
