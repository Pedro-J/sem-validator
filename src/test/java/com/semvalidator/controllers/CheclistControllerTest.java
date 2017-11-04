package com.semvalidator.controllers;

import com.semvalidator.exception.GlobalExceptionHandler;
import com.semvalidator.model.Checklist;
import com.semvalidator.model.Criterion;
import com.semvalidator.model.Requirement;
import com.semvalidator.service.*;
import com.semvalidator.util.AbstractRestControllerTest;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

public class CheclistControllerTest {

    ChecklistController checklistController;

    @Mock
    ChecklistService checklistService;

    @Mock
    QuestionService questionService;

    @Mock
    CriterionService criterionService;

    @Mock
    RequirementService requirementService;

    @Mock
    ModelService modelService;

    @Mock
    AnswerService answerService;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        checklistController = new ChecklistController(checklistService, questionService,
                criterionService, requirementService, modelService, answerService);

        mockMvc = MockMvcBuilders.standaloneSetup(checklistController).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    public void testShowAllChecklist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/checklists/list"))
                .andExpect(MockMvcResultMatchers.view().name("checklists/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("checklists"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(checklistService, Mockito.times(1)).findAll();
    }

    @Test
    public void testShowChecklist() throws Exception {
        Checklist checklist = new Checklist();
        checklist.setId(1);

        Mockito.when(checklistService.findById(Mockito.anyInt())).thenReturn(checklist);

        ModelAndView mav = mockMvc.perform(MockMvcRequestBuilders.get("/checklists/"+ checklist.getId() +"/details"))
                .andExpect(MockMvcResultMatchers.view().name("checklists/detail"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("checklist"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getModelAndView();

        Checklist targetChecklist = (Checklist) mav.getModel().get("checklist");


        Mockito.verify(checklistService, Mockito.times(1)).findById(Mockito.anyInt());
        Assert.assertEquals(targetChecklist.getId(), checklist.getId());
    }

    @Test
    public void testSaveOrUpdateTest() throws Exception {
        Checklist checklist = new Checklist();
        checklist.setId(1);
        checklist.setTitle("checklist test 1");

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/checklists")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(AbstractRestControllerTest.asJsonString(checklist)))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
        .andReturn().getResponse().getContentAsString();

        MatcherAssert.assertThat(response, Matchers.containsString("saved"));
        BDDMockito.then(checklistService).should().save(Mockito.any());
    }

    @Test
    public void testShowAddForm() throws Exception {

        BDDMockito.given(criterionService.findAll()).willReturn(new ArrayList<Criterion>());
        BDDMockito.given(requirementService.findAll()).willReturn(new ArrayList<Requirement>());

        mockMvc.perform(MockMvcRequestBuilders.get("/checklists/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("criterions"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("requirements"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("checklistTypes"))
                .andExpect(MockMvcResultMatchers.model().attribute("newChecklist", true))
                .andExpect(MockMvcResultMatchers.view().name("checklists/form"));

    }

    @Test
    public void testShowUpdateForm() throws Exception {

        Checklist checklist = new Checklist();
        checklist.setId(15);

        BDDMockito.given(checklistService.findById(Mockito.anyInt())).willReturn(checklist);
        BDDMockito.given(criterionService.findAll()).willReturn(new ArrayList<Criterion>());
        BDDMockito.given(requirementService.findAll()).willReturn(new ArrayList<Requirement>());

        mockMvc.perform(MockMvcRequestBuilders.get("/checklists/" + checklist.getId() +"/update"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("criterions"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("requirements"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("checklistTypes"))
                .andExpect(MockMvcResultMatchers.model().attribute("newChecklist", false))
                .andExpect(MockMvcResultMatchers.view().name("checklists/form"))
                .andExpect(MockMvcResultMatchers.model().attribute("checklist", checklist));

    }


}
