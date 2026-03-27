package com.art5019.dikenga_planner.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ProjectDikengaStructure {
    NONE(0,1, "Linear (No Dikenga)", List.of(DikengaPhase.NONE)),
    SIMPLE(1,4, "Ancestral (4 Phases)", List.of(DikengaPhase.MUSONI,DikengaPhase.KALA,DikengaPhase.NSEKE,DikengaPhase.LUVEMBA)),
    DOUBLE(2,8, "The Orisha (8 Phases)", List.of(DikengaPhase.OBATALA,DikengaPhase.YEMOJA,DikengaPhase.IBEJI,DikengaPhase.OSOOSI,DikengaPhase.OGUN,DikengaPhase.NANA,DikengaPhase.OBALUAYE,DikengaPhase.SANGO));

    private final int id;
    private final int phases;
    private final List<DikengaPhase> dp;
    private final String name;

    ProjectDikengaStructure(int id, int phases, String name, List<DikengaPhase> dp) {
        this.id = id;
        this.phases = phases;
        this.dp = dp;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getPhases() {
        return phases;
    }

    public List<DikengaPhase> getDp() {
        return dp;
    }

    public static ProjectDikengaStructure fromId(int id) {
        ProjectDikengaStructure[] structures = ProjectDikengaStructure.values();
        for (ProjectDikengaStructure structure : structures) {
            if (structure.id == id) {
                return structure;
            }
        }
        return ProjectDikengaStructure.NONE;
    }

    public String getName() {
        return name;
    }

}
