package com.qmclouca.base.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qmclouca.base.Dtos.NoteDto;
import com.qmclouca.base.models.Note;
import com.qmclouca.base.services.NoteService;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private ModelMapper modelMapper;

    private final NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public NoteController(NoteService noteService){
        super();
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto note) {

        Note noteRequest = modelMapper.map(note, Note.class);
        if(noteRequest.getNoteTitle() == null || noteRequest.getNoteContent() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Note noteResponse = noteService.createNote(noteRequest);
        NoteDto noteDto = modelMapper.map(noteResponse, NoteDto.class);
        return new ResponseEntity<>(noteDto, HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<NoteDto>> getAllNotesByClientId(@PathVariable Long clientId) {
        List<Note> notes = noteService.getNotesByClient(clientId);
        List<NoteDto> noteDtos = notes.stream().map(note -> modelMapper.map(note, NoteDto.class)).toList();
        return new ResponseEntity<>(noteDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Boolean> deleteNoteById(@PathVariable Long noteId) {
        if(noteService.deleteById(noteId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDto> updateNoteById(@PathVariable Long noteId, @RequestBody NoteDto note) {
        Note noteToUpdate = null;
        try{
            noteToUpdate = noteService.getNoteById(noteId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Note noteRequest = modelMapper.map(note, Note.class);
        noteToUpdate.setTitle(noteRequest.getNoteTitle().toString());
        noteToUpdate.setContent(noteRequest.getNoteContent().toString());
        noteToUpdate.setModifiedAt(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        Note noteResponse = noteService.updateNoteById(noteId, noteToUpdate);
        if(noteResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        NoteDto noteDto = modelMapper.map(noteResponse, NoteDto.class);
        return new ResponseEntity<>(noteDto, HttpStatus.OK);
    }
}
