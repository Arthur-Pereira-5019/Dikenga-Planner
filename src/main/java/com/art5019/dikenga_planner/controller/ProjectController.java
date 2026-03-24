package com.art5019.dikenga_planner.controller;

import com.art5019.dikenga_planner.dto.project.ProjectCreationDTO;
import com.art5019.dikenga_planner.dto.project.ProjectUpdateDescriptionDTO;
import com.art5019.dikenga_planner.dto.project.ProjectUpdateNameDTO;
import com.art5019.dikenga_planner.model.Project;
import com.art5019.dikenga_planner.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

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

    @GetMapping("/{id}")
    public Project findProjectById(@PathVariable("{id}") Long id) {
        return ps.findProjectById(id);
    }

    @GetMapping()
    public List<Project> findAll() {
        return ps.findAllProjects();
    }


    @PatchMapping("/startProject/{id}")
    public ResponseEntity<?> startProject(@PathVariable("{id}") Long id) {
        ps.startProject(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/nextCycle/{id}")
    public ResponseEntity<?> nextCycle(@PathVariable("{id}") Long id) {
        ps.goToNextCycle(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change/description")
    public ResponseEntity<?> patchProjectDescription(@RequestBody ProjectUpdateDescriptionDTO puddto) {
        ps.setDescription(puddto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change/name")
    public ResponseEntity<?> patchProjectName(@RequestBody ProjectUpdateNameDTO pundto) {
        ps.setProjectName(pundto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("{id}") Long id) {
        ps.deleteProject(id);
        return ResponseEntity.ok().build();
    }
}
