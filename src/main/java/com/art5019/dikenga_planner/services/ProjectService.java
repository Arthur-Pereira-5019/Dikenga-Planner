package com.art5019.dikenga_planner.services;

import com.art5019.dikenga_planner.dto.project.ProjectCreationDTO;
import com.art5019.dikenga_planner.dto.project.ProjectUpdateDescriptionDTO;
import com.art5019.dikenga_planner.dto.project.ProjectUpdateNameDTO;
import com.art5019.dikenga_planner.exceptions.project.ProjectNotFoundException;
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

    public Project findProjectById(Long id) {
        return pr.findById(id).orElseThrow(() -> new ProjectNotFoundException("Could not find a project with a matching Id"));
    }

    public void startProject(Long id) {
        Project p = findProjectById(id);
        p.start();
        pr.save(p);
    }

    public Project goToNextCycle(Long id) {
        Project p = findProjectById(id);
        p.goToNextCycle();
        return pr.save(p);
    }

    public void setDescription(ProjectUpdateDescriptionDTO puddto) {
        Project p = findProjectById(puddto.id());
        p.setProjectDescription(puddto.description());
        pr.save(p);
    }

    public void setProjectName(ProjectUpdateNameDTO pundto) {
        Project p = findProjectById(pundto.id());
        p.setProjectDescription(pundto.name());
        pr.save(p);
    }
}
