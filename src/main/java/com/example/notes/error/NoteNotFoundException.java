package com.example.notes.error;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class NoteNotFoundException extends ResponseStatusException {
    public NoteNotFoundException(String message, HttpStatusCode code) {
        super(code, message);
    }
}
