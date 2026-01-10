package com.art5019.dikenga_planner.model;

public enum ProjectDikengaStructure {
    NONE(0,1),
    SIMPLE(1,4),
    DOUBLE(2,8);

    private final int id;
    private final int phases;

    ProjectDikengaStructure(int id, int phases) {
        this.id = id;
        this.phases = phases;
    }

}
