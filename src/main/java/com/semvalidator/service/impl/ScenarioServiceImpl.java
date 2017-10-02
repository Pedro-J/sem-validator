package com.semvalidator.service.impl;

import com.semvalidator.model.Scenario;
import com.semvalidator.model.ScenarioTip;
import com.semvalidator.repository.ScenarioRepository;
import com.semvalidator.repository.ScenarioTipRepository;
import com.semvalidator.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioServiceImpl implements ScenarioService {

    private ScenarioRepository scenarioRepository;

    private ScenarioTipRepository scenarioTipRepository;

    @Autowired
    public ScenarioServiceImpl(ScenarioRepository scenarioRepository, ScenarioTipRepository scenarioTipRepository) {
        this.scenarioRepository = scenarioRepository;
        this.scenarioTipRepository = scenarioTipRepository;
    }


    @Override
    public List<Scenario> findAll() {
        return scenarioRepository.findAll();
    }

    @Override
    public List<ScenarioTip> findAllTips(Integer scenarioId) {
        return scenarioTipRepository.findByScenario(scenarioId);
    }
}
