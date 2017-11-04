package com.semvalidator.controllers;

import com.google.gson.Gson;
import com.semvalidator.exception.GlobalExceptionHandler;
import com.semvalidator.model.Question;
import com.semvalidator.service.CriterionService;
import com.semvalidator.service.QuestionService;
import com.semvalidator.service.RequirementService;
import com.semvalidator.util.SearchQuestionParamsDTO;
import com.semvalidator.validation.QuestionFormValidator;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.anyObject;

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

        questionController = new QuestionController(questionService, criterionService, requirementService);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController)
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    public void testShowAllUsers() throws Exception {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());

        Mockito.when(questionService.findAllOrderByRequirementAndCriterion()).thenReturn(questions);

        mockMvc.perform(MockMvcRequestBuilders.get("/questions/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("questions/list"));

        Mockito.verify(questionService, Mockito.times(1)).findAllOrderByRequirementAndCriterion();
    }

    @Test
    public void testGetAllUsersPageble() throws Exception {

        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        Page<Question> questionsPageble = new PageImpl<Question>(questions);

        ArgumentCaptor<PageRequest> argumentCaptor = ArgumentCaptor.forClass(PageRequest.class);

        Mockito.when(questionService.findAllPageable(Mockito.anyObject())).thenReturn(questionsPageble);

        mockMvc.perform(MockMvcRequestBuilders.get("/questions")
                .param("page", "2")
                .param("size", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));

        Mockito.verify(questionService, Mockito.times(1)).findAllPageable(anyObject());
        Mockito.verify(questionService, Mockito.times(1)).findAllPageable(argumentCaptor.capture());
        PageRequest pageRequest = argumentCaptor.getValue();

        assertThat(pageRequest.getPageNumber(), Matchers.greaterThan(1));
        assertThat(pageRequest.getPageSize(), Matchers.greaterThan(1));
    }


    @Test
    public void testShowQuestionDetails() throws Exception {
        Mockito.when(questionService.findById(Mockito.anyInt())).thenReturn(new Question());
        mockMvc.perform(MockMvcRequestBuilders.get("/questions/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("questions/detail"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("question"));

    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Question question = new Question();
        question.setId(1);
        question.setDescription("description 1");

        mockMvc.perform(MockMvcRequestBuilders.post("/questions")
                .requestAttr("question", question))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/questions/list"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Mockito.verify(questionService, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testDeleteQuestion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/questions/1/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/questions/list"));

        Mockito.verify(questionService, Mockito.times(1)).delete(Mockito.anyInt());
    }

    @Test
    public void testSearch() throws Exception {
        SearchQuestionParamsDTO dto = new SearchQuestionParamsDTO();
        dto.setPage(1);
        dto.setSize(1);
        Gson gson = new Gson();

        List<Question> questions = new ArrayList<>();

        Question question1 = new Question();
        question1.setId(1);
        question1.setDescription("This is the question 1");

        Question question2 = new Question();
        question2.setId(2);
        question2.setDescription("This is the question 2");

        questions.add(question1);
        questions.add(question2);

        Page<Question> pageQuestions = new PageImpl<Question>(questions);

        Mockito.when(questionService.search(Mockito.anyObject())).thenReturn(pageQuestions);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/questions/search")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(gson.toJson(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        String responseBody = response.getContentAsString();
        assertThat(responseBody, Matchers.containsString("This is the question 1"));
        assertThat(responseBody, Matchers.containsString("This is the question 2"));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        Question questionReturn = new Question();
        questionReturn.setDescription("This is the Question 1");

        Mockito.when(questionService.findById(1)).thenReturn(questionReturn);

        Mockito.when(criterionService.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(requirementService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/questions/1/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("question", questionReturn))
                .andExpect(MockMvcResultMatchers.model().attribute("criterions", new ArrayList<>()))
                .andExpect(MockMvcResultMatchers.model().attribute("requirements", new ArrayList<>()))
                .andExpect(MockMvcResultMatchers.view().name("questions/form"));
    }

    @Test
    public void testShowAddForm() throws Exception {

        Mockito.when(criterionService.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(requirementService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/questions/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("question", new Question()))
                .andExpect(MockMvcResultMatchers.model().attribute("criterions", new ArrayList<>()))
                .andExpect(MockMvcResultMatchers.model().attribute("requirements", new ArrayList<>()))
                .andExpect(MockMvcResultMatchers.view().name("questions/form"));
    }
}
