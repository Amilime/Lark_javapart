package org.example.controller;

import org.example.entity.Note;
import org.example.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //Response data interface
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteMapper noteMapper;

    @GetMapping   //query all note
    public List<Note> getAll(){
        return noteMapper.selectList(null);
    }

    @PostMapping// add new note
    public String add(@RequestBody Note note){
        noteMapper.insert(note);
        return "Done!";
    }
}
