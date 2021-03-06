package com.semvalidator.service.impl;

import com.semvalidator.enums.AnswerValue;
import com.semvalidator.model.Answer;
import com.semvalidator.model.Evaluation;
import com.semvalidator.model.Question;
import com.semvalidator.repository.AnswerRepository;
import com.semvalidator.repository.ChecklistRepository;
import com.semvalidator.repository.EvaluationRepository;
import com.semvalidator.repository.QuestionRepository;
import com.semvalidator.service.AnswerService;
import com.semvalidator.util.AnswerJsonDTO;
import com.semvalidator.util.OptionHTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService{

    private AnswerRepository answerRepository;

    private QuestionRepository questionRepository;

    private EvaluationRepository evaluationRepository;

    private ChecklistRepository checklistRepository;

    private ApplicationContext context;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, EvaluationRepository evaluationRepository,
                             QuestionRepository questionRepository, ChecklistRepository checklistRepository,
                             ApplicationContext context) {
        this.answerRepository = answerRepository;
        this.evaluationRepository = evaluationRepository;
        this.questionRepository = questionRepository;
        this.checklistRepository = checklistRepository;
        this.context = context;
    }

    public Answer save(Answer entity) {
        return answerRepository.saveAndFlush(entity);
    }

    public Answer update(Answer entity) {
        return answerRepository.saveAndFlush(entity);
    }

    @Transactional(readOnly = true)
    public Answer findById(Integer id) {
        return answerRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public void delete(Integer id) {
        answerRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Page<Answer> findAllPageable(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    public void saveAllDatas(List<AnswerJsonDTO> answersJson) {

        if( answersJson != null && !answersJson.isEmpty() ) {
            Evaluation evaluation = evaluationRepository.findOne(answersJson.get(0).getEvaluation());

            List<Question> questionsNotAnswered = questionRepository.findByChecklist(evaluation.getChecklist().getId());

            if( isSaveAll(answersJson) ){
                saveNotAnsweredQuestions(questionsNotAnswered, evaluation.getId());
                return;
            }

            List<Answer> answers = new ArrayList<>();
            for (AnswerJsonDTO dto : answersJson) {

                Answer answer = new Answer(dto);

                questionsNotAnswered.remove(answer.getQuestion());

                if (alreadyExist(answer)) {
                    answer = answerRepository.findByEvaluationAndQuestion(
                            answer.getEvaluation(), answer.getQuestion());
                    answer.convertFromDTO(dto);
                    answerRepository.saveAndFlush(answer);

                } else {
                    answers.add(answer);
                }
            }
            answerRepository.save(answers);
            saveNotAnsweredQuestions(questionsNotAnswered, evaluation.getId());
        }
    }

    private boolean isSaveAll(List<AnswerJsonDTO> answersJson) {
        if( answersJson.size() == 1){
            AnswerJsonDTO dto = answersJson.get(0);
            if( dto.getQuestion() == 0 && dto.getValue() == 0){
                return true;
            }
        }
        return false;
    }

    private void saveNotAnsweredQuestions(List<Question> questionsNotAnswered, Integer currentEvaluationID) {

        for(Question question : questionsNotAnswered){
            AnswerJsonDTO dto = new AnswerJsonDTO();
            dto.setQuestion(question.getId());
            dto.setEvaluation(currentEvaluationID);
            dto.setValue(AnswerValue.PLEASED.getCode()); //Default answer

            Answer answer = new Answer(dto);

            if (alreadyExist(answer)) {
                answer = answerRepository.findByEvaluationAndQuestion(
                        answer.getEvaluation(), answer.getQuestion());
                answer.convertFromDTO(dto);
                answerRepository.saveAndFlush(answer);
            }else{
                answerRepository.saveAndFlush(answer);
            }
        }
    }

    @Override
    public List<Answer> findByEvaluation(Integer id) {
        return answerRepository.findByEvaluation(id);
    }

    @Override
    public List<OptionHTML> getTypes() {

        List<OptionHTML> options = new ArrayList<OptionHTML>();

        for(AnswerValue value : AnswerValue.values()){
            String message = context.getMessage(value.getMessageCode(), null,  LocaleContextHolder.getLocale());
            OptionHTML optionHTML = new OptionHTML(value.getCode(), message);
            options.add(optionHTML);
        }
        return options;
    }

    @Transactional(readOnly = true )
    public boolean alreadyExist(Answer answer){
        Answer savedAnswer = answerRepository.findByEvaluationAndQuestion(
                answer.getEvaluation(), answer.getQuestion());

        if( savedAnswer != null ){
            return true;
        }
        return false;
    }
}
