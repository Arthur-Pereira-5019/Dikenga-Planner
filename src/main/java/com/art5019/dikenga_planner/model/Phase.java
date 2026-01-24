package com.art5019.dikenga_planner.model;

import com.art5019.dikenga_planner.exceptions.InvalidDescription;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private DikengaPhase actualPhase;

    @OneToMany
    private List<Task> tasks;

    @Column
    private ZonedDateTime finishingDate;

    @Column
    private ZonedDateTime finishedDate;

    @Column
    private Boolean shouldAutoFinish;

    @Column(length = 8191,columnDefinition = "TEXT")
    private String annotations;

    @Column
    private Boolean finished = false;

    @Column
    private Integer phaseNumber;


    public Phase() {
    }

    public Phase(DikengaPhase actualPhase, Integer phaseNumber) {
        this.actualPhase = actualPhase;
        this.phaseNumber = phaseNumber;
    }

    public Long getId() {
        return id;
    }

    public DikengaPhase getActualPhase() {
        return actualPhase;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task... tasks) {
        this.tasks.addAll(List.of(tasks));
    }

    public void removeTasks(Task... tasks) {
        this.tasks.removeAll(List.of(tasks));
    }

    public ZonedDateTime getFinishingDate() {
        return finishingDate;
    }

    public void setFinishingDate(ZonedDateTime finishingDate) {
        shouldAutoFinish = true;
        this.finishingDate = finishingDate;
    }

    public void removeFinishingDate() {
        finishingDate = null;
        shouldAutoFinish = false;
    }

    public Boolean shouldAutoFinish() {
        return shouldAutoFinish;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        if(annotations.length() > 8191) {
            throw new InvalidDescription("Description is way to long!");
        }
        this.annotations = annotations;
    }

    public Boolean isFinished() {
        return finished;
    }

    public void finish() {
        finishedDate = ZonedDateTime.now();
        finished = true;
    }

    public void unfinish() {
        finishedDate = null;
        finished = false;
    }

    public ZonedDateTime getFinishedDate() {
        return finishedDate;
    }


    /*
    public void setFinishedDate(ZonedDateTime finishedDate) {
        if(ZonedDateTime.from(Instant.now()).isBefore(finishedDate)) {
            throw new InvalidTaskFinishDate("Task finished after the current date!");
        }
        if(finishedDate != null) {
            if(finishedDate.isBefore(finishedDate)) {
                throw new InvalidTaskFinishDate("Task finished before it's creation!");
            }
        }
        this.finishedDate = finishedDate;
    }*/
}
