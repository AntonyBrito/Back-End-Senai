package com.example.aula.Controller;

import com.example.aula.Entity.Curso;
import com.example.aula.Entity.Professor;
import com.example.aula.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Curso>> getAll() {
        List<Curso> cursos = cursoRepository.findAll();
        if (cursos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cursos);
        }
    }

    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody Curso curso){
        Curso cursoBd = cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cursoBd);
    }
}
