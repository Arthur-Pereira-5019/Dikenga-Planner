package com.art5019.dikenga_planner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

@Entity
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



}
