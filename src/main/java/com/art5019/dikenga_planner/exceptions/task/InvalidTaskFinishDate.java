package com.art5019.dikenga_planner.exceptions.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTaskFinishDate extends RuntimeException {
    public InvalidTaskFinishDate(String message) {
        super(message);
    }
}
