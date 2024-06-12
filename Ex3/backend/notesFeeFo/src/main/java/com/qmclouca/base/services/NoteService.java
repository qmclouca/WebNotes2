package com.qmclouca.base.services;

import com.qmclouca.base.models.Note;

import java.util.List;

public interface NoteService {
    Note createNote(Note note);
    List<Note> getNotesByClient(Long clientId);
    Note getNoteById(Long noteId);
    Note updateNoteById(Long noteId, Note note);
    boolean deleteById(Long noteId);
}
