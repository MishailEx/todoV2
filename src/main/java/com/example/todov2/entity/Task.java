package com.example.todov2.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Task {
    private long id;
    private String title;
    private Integer completed;
    private Date date;
    private Priority priority;
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @Basic
    @Column(name = "title", length = 45)
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "completed")
    public Integer getCompleted() {
        return completed;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    public Priority getPriority() {
        return priority;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategory() {
        return category;
    }

}
