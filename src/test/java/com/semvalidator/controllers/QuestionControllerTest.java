package com.semvalidator.controllers;

import com.semvalidator.exception.GlobalExceptionHandler;
import com.semvalidator.model.Question;
import com.semvalidator.service.CriterionService;
import com.semvalidator.service.QuestionService;
import com.semvalidator.service.RequirementService;
import com.semvalidator.validation.QuestionFormValidator;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class QuestionControllerTest {

    @Mock
    QuestionService questionService;

    @Mock
    CriterionService criterionService;

    @Mock
    RequirementService requirementService;

    @Mock
    QuestionFormValidator questionFormValidator;

    QuestionController questionController;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        questionController = new QuestionController(questionService, criterionService, requirementService, questionFormValidator);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController)
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    public void testShowAllUsers() throws Exception {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());

        when(questionService.findAll()).thenReturn(questions);

        mockMvc.perform(MockMvcRequestBuilders.get("/questions/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("questions/list"));

        verify(questionService, times(1)).findAll();
    }

    @Test
    public void testGetAllUsersPageble() throws Exception {

        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        Page<Question> questionsPageble = new PageImpl<Question>(questions);

        ArgumentCaptor<PageRequest> argumentCaptor = ArgumentCaptor.forClass(PageRequest.class);

        when(questionService.findAllPageable(anyObject())).thenReturn(questionsPageble);

        mockMvc.perform(MockMvcRequestBuilders.get("/questions")
                .param("page", "2")
                .param("size", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));

        verify(questionService, times(1)).findAllPageable(anyObject());
        verify(questionService, times(1)).findAllPageable(argumentCaptor.capture());
        PageRequest pageRequest = argumentCaptor.getValue();

        assertThat(pageRequest.getPageNumber(), Matchers.greaterThan(1));
        assertThat(pageRequest.getPageSize(), Matchers.greaterThan(1));
    }

}
