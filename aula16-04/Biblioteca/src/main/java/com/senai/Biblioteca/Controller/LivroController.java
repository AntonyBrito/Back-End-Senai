package com.senai.Biblioteca.Controller;

import com.senai.Biblioteca.DTO.LivroDTO;
import com.senai.Biblioteca.Entity.Livro;
import com.senai.Biblioteca.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> getAll(){
        return livroService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> getById(@PathVariable Long id){
        Optional<LivroDTO> livroDTOOptional = livroService.getById(id);
        if(livroDTOOptional.isPresent()){
            return ResponseEntity.ok(livroDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LivroDTO> create(@RequestBody LivroDTO livroDTO){
        LivroDTO livroDTOSave = livroService.create(livroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroDTOSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Long id, @RequestBody LivroDTO livroDTO){
        Optional<LivroDTO> livroDTOOptional = livroService.updateLivro(id, livroDTO);
        if(livroDTOOptional.isPresent()){
            return ResponseEntity.ok(livroDTOOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(livroService.delete(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
