package com.art5019.dikenga_planner.controller;

import com.art5019.dikenga_planner.dto.project.ProjectCreationDTO;
import com.art5019.dikenga_planner.model.Project;
import com.art5019.dikenga_planner.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService ps;

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectCreationDTO p) {
        ps.createProject(p).getId();
        return ResponseEntity.ok().build();
    }
}
