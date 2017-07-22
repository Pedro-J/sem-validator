package com.semvalidator.service.impl;

import com.semvalidator.enums.AnswerValue;
import com.semvalidator.model.Answer;
import com.semvalidator.model.Question;
import com.semvalidator.repository.AnswerRepository;
import com.semvalidator.repository.ChecklistRepository;
import com.semvalidator.repository.QuestionRepository;
import com.semvalidator.service.AnswerService;
import com.semvalidator.util.AnswerJsonDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ChecklistRepository checklistRepository;

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
            Integer currentChecklist = answersJson.get(0).getChecklist();
            List<Question> questionsNotAnswered = questionRepository.findByChecklist(currentChecklist);

            if( isSaveAll(answersJson) ){
                saveNotAnsweredQuestions(questionsNotAnswered, currentChecklist);
                return;
            }

            List<Answer> answers = new ArrayList<>();
            for (AnswerJsonDTO dto : answersJson) {

                Answer answer = new Answer(dto);

                questionsNotAnswered.remove(answer.getQuestion());

                if (alreadyExist(answer)) {
                    answer = answerRepository.findByChecklistAndQuestion(
                            answer.getChecklist(), answer.getQuestion());
                    answer.convertFromDTO(dto);
                    answerRepository.saveAndFlush(answer);

                } else {
                    answers.add(answer);
                }
            }
            answerRepository.save(answers);
            saveNotAnsweredQuestions(questionsNotAnswered, currentChecklist);
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

    private void saveNotAnsweredQuestions(List<Question> questionsNotAnswered, Integer currentChecklistID) {

        for(Question question : questionsNotAnswered){
            AnswerJsonDTO dto = new AnswerJsonDTO();
            dto.setQuestion(question.getId());
            dto.setChecklist(currentChecklistID);
            dto.setValue(AnswerValue.APPLY.getCode()); //Default answer

            Answer answer = new Answer(dto);

            if (alreadyExist(answer)) {
                answer = answerRepository.findByChecklistAndQuestion(
                        answer.getChecklist(), answer.getQuestion());
                answer.convertFromDTO(dto);
                answerRepository.saveAndFlush(answer);
            }else{
                answerRepository.saveAndFlush(answer);
            }
        }
    }

    @Override
    public List<Answer> findByChecklist(Integer id) {
        return answerRepository.findByChecklist(id);
    }

    @Transactional(readOnly = true )
    public boolean alreadyExist(Answer answer){
        Answer savedAnswer = answerRepository.findByChecklistAndQuestion(
                answer.getChecklist(), answer.getQuestion());

        if( savedAnswer != null ){
            return true;
        }
        return false;
    }
}
