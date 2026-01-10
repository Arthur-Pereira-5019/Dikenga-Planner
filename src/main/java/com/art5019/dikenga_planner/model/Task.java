package com.art5019.dikenga_planner.model;

import com.art5019.dikenga_planner.exceptions.task.InvalidTaskName;
import jakarta.persistence.*;

import java.util.Date;

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
    private Date creationDate;

    @Column
    private Date finishedDate;

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
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String treatName(String name) {
        if(name == null) {
            throw new InvalidTaskName("Null-named task!");
        }
        if(name.isBlank()) {
            throw new InvalidTaskName("Blank-named task!");
        }
        if(name.length() > 512) {
            throw new InvalidTaskName("Long named task!");
        }
        return name;
    }
    public String treatDescription(String desc) {
        if(desc.length() > 4095) {
            throw new InvalidTaskName("Description is way to long!");
        }
        return desc;
    }
}
