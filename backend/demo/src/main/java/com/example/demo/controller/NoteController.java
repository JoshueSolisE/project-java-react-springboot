package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/notes")
@CrossOrigin(origins = "http://localhost:5173")
public class NoteController {

    @Autowired NoteRepository noteRepository;

    //Get all Notes
    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    //Post a new Note
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    //Edit a Note
    @PostMapping("/{id}")
    public Note editNote(@PathVariable Long id, @RequestBody Note noteDetails) {
        Optional<Note> optionalNote = noteRepository.findById(id);

        if (optionalNote.isPresent()){
            Note note = optionalNote.get();
            note.setTitle(noteDetails.getTitle());
            note.setDescription(noteDetails.getDescription());

            return noteRepository.save(note);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);

        if (optionalNote.isPresent()) {
            noteRepository.delete(optionalNote.get());
            return "Note has been deleted";
        } else {
            return null;
        }
    }
}
