package com.art5019.dikenga_planner.dto.project;

import com.art5019.dikenga_planner.model.ProjectDikengaStructure;

public record ProjectSimpleDisplayDTO(Long id, ProjectDikengaStructure dikengaStructure, boolean started, String projectName, int currentPhaseNumber) {
}
