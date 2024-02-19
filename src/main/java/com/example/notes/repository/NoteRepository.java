package com.example.notes.repository;

import com.example.notes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>{
    List<Note> findByCreatedByOrderByIdDesc(String createdBy);
    List<Note> findByCompleted(boolean completed);


}
