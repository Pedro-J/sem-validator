package com.semvalidator.service;

import com.semvalidator.model.Scenario;
import com.semvalidator.model.ScenarioTip;

import java.util.List;

public interface ScenarioService {

    List<Scenario> findAll();

    List<ScenarioTip> findAllTips(Integer scenarioId);

}
