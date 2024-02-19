package com.example.notes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Id;

@Entity
public class Note {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private String createdBy;

    private boolean completed;

    protected Note(){};

    public Note(String content, String createdBy, boolean completed) {
        this.content = content;
        this.createdBy = createdBy;
        this.completed = completed;
    };


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getCompleted() {
        return  this.completed;
    }


    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", completed=" + completed +
                '}';
    }
}
