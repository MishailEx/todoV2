package com.example.todov2.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Priority {
    private long id;
    private String title;
    private String color;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }
}
