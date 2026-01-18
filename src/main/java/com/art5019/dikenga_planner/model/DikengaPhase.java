package com.art5019.dikenga_planner.model;

public enum DikengaPhase {
    NONE(0,"None"),
    MUSONI(0,"Musoni"),
    KALA(1,"Kala"),
    NSEKE(2,"Nseke"),
    LUVEMBA(3,"Luvemba"),
    OBATALA(0,"Obatalá"),
    YEMOJA(1,"Yemoja"),
    IBEJI(2,"Ibeji"),
    OSOOSI(3,"Osoosi"),
    OGUN(4,"Ogun"),
    NANA(5,"Nana"),
    OBALUAYE(6,"Obaluaye"),
    SANGO(7,"Sango");

    private final int phase_number;
    private final String name;

    DikengaPhase(int phase_number, String name) {
        this.phase_number = phase_number;
        this.name = name;
    }

}
