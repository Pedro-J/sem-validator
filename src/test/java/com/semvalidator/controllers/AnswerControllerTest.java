package com.semvalidator.controllers;

import com.google.gson.Gson;
import com.semvalidator.builder.AnswerDTOBuilder;
import com.semvalidator.exception.GlobalExceptionHandler;
import com.semvalidator.service.AnswerService;
import com.semvalidator.util.AbstractRestControllerTest;
import com.semvalidator.util.AnswerJsonDTO;
import com.semvalidator.util.OptionHTML;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


public class AnswerControllerTest {

    @Mock
    AnswerService answerService;

    @Mock
    ApplicationContext context;

    AnswerController answerController;

    MockMvc mockMvc;


    private Gson gson = new Gson();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        answerController = new AnswerController(answerService, context);
        mockMvc = MockMvcBuilders.standaloneSetup(answerController)
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    public void testGetAnswers() throws Exception {
        List<OptionHTML> options = new ArrayList<>();
        OptionHTML optionHTML = new OptionHTML(1, "");
        options.add(optionHTML);

        BDDMockito.given(answerService.getTypes()).willReturn(options);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/answers/types"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse();

        String responseContent = response.getContentAsString();
        List answerTypes = gson.fromJson(responseContent, List.class);
        Assert.assertTrue(responseContent.contains("1"));
        Assert.assertTrue( answerTypes.size() == 1);

    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        List<AnswerJsonDTO> answers = new ArrayList<>();
        AnswerDTOBuilder answerDTOBuilder = new AnswerDTOBuilder();
        AnswerJsonDTO answer1 = answerDTOBuilder.forQuestion(1).inEvaluation(2).answeredWith(1).build();
        AnswerJsonDTO answer2 = answerDTOBuilder.forQuestion(2).inEvaluation(2).answeredWith(0).build();
        answers.add(answer1);
        answers.add(answer2);

        mockMvc.perform(MockMvcRequestBuilders.post("/answers")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(AbstractRestControllerTest.asJsonString(answers)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("saved")));
    }

}
