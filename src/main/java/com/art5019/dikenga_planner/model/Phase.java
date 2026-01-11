package com.art5019.dikenga_planner.model;

import com.art5019.dikenga_planner.exceptions.task.InvalidDescription;
import com.art5019.dikenga_planner.exceptions.task.InvalidTaskFinishDate;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private ProjectDikengaStructure name;

    @OneToMany
    private List<Task> tasks;

    @Column
    private ZonedDateTime finishingDate;

    @Column
    private ZonedDateTime finishedDate;

    @Column
    private Boolean shouldAutoFinish;

    @Column(length = 8191)
    private String annotations;

    @Column
    private Boolean finished = false;


    public Phase() {
    }

    public Phase(ProjectDikengaStructure name, ZonedDateTime finishingDate, Boolean shouldAutoFinish) {
        this.name = name;
        this.finishingDate = finishingDate;
        this.shouldAutoFinish = shouldAutoFinish;
    }

    public Long getId() {
        return id;
    }


    public ProjectDikengaStructure getName() {
        return name;
    }

    public void setName(ProjectDikengaStructure name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public ZonedDateTime getFinishingDate() {
        return finishingDate;
    }

    public void setFinishingDate(ZonedDateTime finishingDate) {
        this.finishingDate = finishingDate;
    }

    public Boolean getShouldAutoFinish() {
        return shouldAutoFinish;
    }

    public void setShouldAutoFinish(Boolean shouldAutoFinish) {
        this.shouldAutoFinish = shouldAutoFinish;
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
