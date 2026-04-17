package com.art5019.dikenga_planner.mapper;

import com.art5019.dikenga_planner.dto.project.ProjectSimpleDisplayDTO;
import com.art5019.dikenga_planner.model.Project;
import com.art5019.dikenga_planner.dto.project.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectMapper {
    public ProjectSimpleDisplayDTO simpleDisplay(Project p) {
        return new ProjectSimpleDisplayDTO(p.getId(),p.getDikengaStructure(),p.isStarted(),p.getProjectName(),p.getCurrentPhaseNumber());
    }

    public List<ProjectSimpleDisplayDTO> simpleDisplayList(List<Project> p) {
        List<ProjectSimpleDisplayDTO> list = new ArrayList<>();
        p.forEach(x -> list.add(simpleDisplay(x)));
        return list;
    }

}
