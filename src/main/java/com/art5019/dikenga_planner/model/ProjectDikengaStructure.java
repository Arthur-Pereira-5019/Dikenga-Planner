package com.art5019.dikenga_planner.model;

import java.util.List;

public enum ProjectDikengaStructure {
    NONE(0,1, List.of(DikengaPhase.NONE)),
    SIMPLE(1,4, List.of(DikengaPhase.MUSONI,DikengaPhase.KALA,DikengaPhase.NSEKE,DikengaPhase.LUVEMBA)),
    DOUBLE(2,8, List.of(DikengaPhase.OBATALA,DikengaPhase.YEMOJA,DikengaPhase.IBEJI,DikengaPhase.OSOOSI,DikengaPhase.OGUN,DikengaPhase.NANA,DikengaPhase.OBALUAYE,DikengaPhase.SANGO));

    private final int id;
    private final int phases;
    private final List<DikengaPhase> dp;

    ProjectDikengaStructure(int id, int phases, List<DikengaPhase> dp) {
        this.id = id;
        this.phases = phases;
        this.dp = dp;
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
}
