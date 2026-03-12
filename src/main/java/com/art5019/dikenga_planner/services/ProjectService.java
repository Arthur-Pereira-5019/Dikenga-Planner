package com.art5019.dikenga_planner.services;

import com.art5019.dikenga_planner.dto.project.ProjectCreationDTO;
import com.art5019.dikenga_planner.model.Project;
import com.art5019.dikenga_planner.model.ProjectDikengaStructure;
import com.art5019.dikenga_planner.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository pr;

    public Project createProject(ProjectCreationDTO pdto) {
        Project p = new Project(ProjectDikengaStructure.fromId(pdto.dikengaStructureId()),pdto.name());
        return pr.save(p);
    }
}
