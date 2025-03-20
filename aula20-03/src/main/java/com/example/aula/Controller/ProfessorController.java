package com.example.aula.Controller;

import com.example.aula.Entity.Professor;
import com.example.aula.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<Professor>> getAll() {
        List<Professor> professors = professorRepository.findAll();
        if (professors.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(professors);
        }
    }

    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody Professor professor){
        Professor professorBd = professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(professorBd);
    }
}
