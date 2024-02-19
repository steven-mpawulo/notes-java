package com.example.notes.Controller;

import com.example.notes.error.NoteNotFoundException;
import com.example.notes.models.Note;
import com.example.notes.repository.NoteRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdBy;

@RestController
@RequestMapping("/api/v1")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("/notes/{noteId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<Note> getNote(@PathVariable Long noteId){
        Optional<Note> note = noteRepository.findById(noteId);
        if (note.isPresent()) {
            return note;
        } else {
            throw new NoteNotFoundException("note with id:" + noteId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/notes")
    public Note addNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @DeleteMapping("/notes/{noteId}")
    public void deleteNote(@PathVariable Long noteId) {
        Optional<Note> note = noteRepository.findById(noteId);
        if (note.isPresent()) {
            noteRepository.deleteById(noteId);
        } else {
            throw new NoteNotFoundException("note already deleted", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/notes/{noteId}")
    public Note updateNote(@PathVariable Long noteId, @RequestBody Note newNote) {
        Optional<Note> note = noteRepository.findById(noteId);
        if (note.isPresent()) {
            Note actualNote = note.get();
            actualNote.setContent(newNote.getContent());
            actualNote.setCreatedBy(newNote.getCreatedBy());
            return noteRepository.save(actualNote);
        } else {
            throw new NoteNotFoundException("note to update not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/notes/filter/{createdBy}")
    public List<Note> getNotesCreatedBy(@PathVariable String createdBy) {

        return noteRepository.findByCreatedByOrderByIdDesc(createdBy);
    }

    @PutMapping("/notes/completed/{noteId}")
    public Note setCompleted(@PathVariable Long noteId, @RequestBody Note note) {
        Optional<Note> oldNote = noteRepository.findById(noteId);
        if (oldNote.isPresent()) {
            Note actualNote = oldNote.get();
            actualNote.setCompleted(note.getCompleted());
            return   noteRepository.save(actualNote);
        } else {
            throw new NoteNotFoundException("no note found", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/notes/status")
    public List<Note> getNotesDependingOnCompletionStatus(@RequestParam boolean completed) {
        return noteRepository.findByCompleted(completed);
    }

}
