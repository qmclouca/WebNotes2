package com.qmclouca.base.services.Implementations;

import com.qmclouca.base.Dtos.NoteDto;
import com.qmclouca.base.models.Note;
import com.qmclouca.base.repositories.NoteRepository;
import com.qmclouca.base.services.NoteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImplementation implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImplementation(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public List<Note> getNotesByClient(Long clientId) {
        return noteRepository.findAllByClientId(clientId);
    }

    @Override
    public Note getNoteById(Long noteId) {
        return noteRepository.getById(noteId);
    }


    @Override
    public boolean deleteById(Long noteId) {
        try
        {
            noteRepository.deleteById(noteId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Note updateNoteById(Long noteId, Note note) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        if(optionalNote.isPresent()) {
            Note existingNote = optionalNote.get();
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
            existingNote.setModifiedAt(note.getModifiedAt());
            return noteRepository.save(existingNote);
        }
        return null;
    }
}
