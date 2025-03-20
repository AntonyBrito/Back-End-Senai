package com.example.aula.Controller;

import com.example.aula.Entity.Aluno;
import com.example.aula.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping


    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno){
        Aluno alunoBd = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }
}
