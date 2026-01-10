package com.art5019.dikenga_planner.exceptions;

import java.util.Date;

public record ExceptionResponse(Date date, String message, String details) {

}
