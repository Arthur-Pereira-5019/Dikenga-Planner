package com.art5019.dikenga_planner.model;

public enum DikengaPhase {
    NONE(0),
    MUSONI(0),
    KALA(1),
    NSEKE(2),
    LUVEMBA(3),
    OBATALA(0),
    YEMOJA(1),
    IBEJI(2),
    OSOOSI(3),
    OGUN(4),
    NANA(5),
    OBALUAYE(6),
    SANGO(7);

    private final int phase_number;

    DikengaPhase(int phase_number) {
        this.phase_number = phase_number;
    }

}
