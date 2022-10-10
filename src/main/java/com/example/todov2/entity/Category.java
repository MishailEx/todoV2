package com.example.todov2.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Category {
    private long id;
    private String title;
    private Long completedCount;
    private Long uncompletedCount;

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
    @Column(name = "completed_count")
    public Long getCompletedCount() {
        return completedCount;
    }

    @Basic
    @Column(name = "uncompleted_count")
    public Long getUncompletedCount() {
        return uncompletedCount;
    }
}
