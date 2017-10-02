package com.semvalidator.repository;

import com.semvalidator.model.ScenarioTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScenarioTipRepository extends JpaRepository<ScenarioTip, Integer> {

    @Query("select st from ScenarioTip st where st.scenario.id = :scanarioId order by st.scenario.description")
    List<ScenarioTip> findByScenario(@Param("scanarioId") Integer scenarioId);
}
