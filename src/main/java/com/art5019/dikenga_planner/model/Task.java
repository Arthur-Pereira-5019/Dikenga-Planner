package com.art5019.dikenga_planner.model;

import com.art5019.dikenga_planner.exceptions.task.InvalidDescription;
import com.art5019.dikenga_planner.exceptions.task.InvalidTaskFinishDate;
import com.art5019.dikenga_planner.exceptions.task.InvalidName;
import com.art5019.dikenga_planner.exceptions.task.InvalidTaskPriority;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.ZonedDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 511)
    private String name;

    @Column(length = 4095)
    private String desc;

    @Column
    private Boolean finished = false;

    @Column
    private ZonedDateTime creationDate;

    @Column
    private ZonedDateTime finishedDate;

    @Column
    private int priority;


    public Task(String name, String desc, ZonedDateTime creationDate, int priority) {
        if(name == null) {
            throw new InvalidName("Null-named task!");
        }
        if(name.isBlank()) {
            throw new InvalidName("Blank-named task!");
        }
        if(name.length() > 511) {
            throw new InvalidName("Long named task!");
        }
        if(desc.length() > 4095) {
            throw new InvalidDescription("Description is way to long!");
        }
        if(priority > 10) {
            throw new InvalidTaskPriority("Way too big priority");
        }
        if(priority < 0) {
            throw new InvalidTaskPriority("Negative priority!");
        }
        this.name = name;
        this.desc = desc;
        this.creationDate = creationDate;
        this.priority = priority;
    }

    public Task() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null) {
            throw new InvalidName("Null-named task!");
        }
        if(name.isBlank()) {
            throw new InvalidName("Blank-named task!");
        }
        if(name.length() > 511) {
            throw new InvalidName("Long named task!");
        }
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        if(desc.length() > 4095) {
            throw new InvalidDescription("Description is way to long!");
        }
        this.desc = desc;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        if(finished) {
            finishedDate = ZonedDateTime.from(Instant.now());
        } else {
            finishedDate = null;
        }
        this.finished = finished;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        if(ZonedDateTime.from(Instant.now()).isBefore(creationDate)) {
            throw new InvalidTaskFinishDate("Task create after the current date!");
        }
        this.creationDate = creationDate;
    }

    public ZonedDateTime getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(ZonedDateTime finishedDate) {
        if(ZonedDateTime.from(Instant.now()).isBefore(finishedDate)) {
            throw new InvalidTaskFinishDate("Task finished after the current date!");
        }
        if(creationDate != null) {
            if(finishedDate.isBefore(creationDate)) {
                throw new InvalidTaskFinishDate("Task finished before it's creation!");
            }
        }
        this.finishedDate = finishedDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if(priority > 10) {
            throw new InvalidTaskPriority("Way too big priority");
        }
        if(priority < 0) {
            throw new InvalidTaskPriority("Negative priority!");
        }
        this.priority = priority;
    }
}
