package com.art5019.dikenga_planner.controller;

import com.art5019.dikenga_planner.model.DikengaPhase;
import com.art5019.dikenga_planner.model.ProjectDikengaStructure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class DataController {
    @GetMapping("/project_types")
    public ProjectDikengaStructure[] getDikengaStructures() {
        return ProjectDikengaStructure.values();
    }
}
