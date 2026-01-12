package com.art5019.dikenga_planner.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private ProjectDikengaStructure dikengaStructure;

    @OneToMany
    private List<Phase> phases;

    @Column
    private boolean started;

    @Column
    private String projectName;

    @Column
    private String projectDescritiption;

    @Column
    private int currentPhase;






}
