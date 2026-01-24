package com.art5019.dikenga_planner.model;

import com.art5019.dikenga_planner.exceptions.InvalidName;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private ProjectDikengaStructure dikengaStructure;

    @OneToMany
    private List<Phase> phases = new ArrayList<>();

    @Column
    private boolean started;

    @Column
    private String projectName;

    @Column(length = 16383,columnDefinition = "TEXT")
    private String projectDescription;

    @Column
    private int currentPhaseNumber;

    public Long getId() {
        return id;
    }

    public ProjectDikengaStructure getDikengaStructure() {
        return dikengaStructure;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setProjectName(String name) {
        if(name == null || name.isBlank()) {
            throw new InvalidName("Empty project name!");
        }
        if(name.length() > 255) {
            throw new InvalidName("Name way too long");
        }
        this.projectName = name;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectDescription(String description) {
        if(description == null) {
            description = "";
        }
        if(description.length() > 255) {
            throw new InvalidName("Name way too long");
        }
        projectDescription = description;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void start() {
        started = true;
    }

    public boolean isStarted() {
        return started;
    }

    public void nextCycle() {
        int pn = phases.size()-1;
        List<DikengaPhase> mp = dikengaStructure.getDp();
        List<Phase> np = new ArrayList<>();
        for(DikengaPhase p: mp) {
            np.add(new Phase(p,pn++));
        }
        phases.addAll(np);
        currentPhaseNumber++;
    }

    public Phase getCurrentPhase() {
        return phases.get(currentPhaseNumber);
    }

    public Project(ProjectDikengaStructure dikengaStructure, String projectName) {
        if(projectName == null || projectName.isBlank()) {
            throw new InvalidName("Empty project name!");
        }
        if(projectName.length() > 255) {
            throw new InvalidName("Name way too long");
        }
        this.dikengaStructure = dikengaStructure;
        this.projectName = projectName;
    }


}
