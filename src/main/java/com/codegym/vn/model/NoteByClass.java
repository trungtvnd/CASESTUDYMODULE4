package com.codegym.vn.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class NoteByClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date date;

    @ManyToOne
    private Classes idClasses;

    public NoteByClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Classes getIdClasses() {
        return idClasses;
    }

    public void setIdClasses(Classes idClasses) {
        this.idClasses = idClasses;
    }
}
