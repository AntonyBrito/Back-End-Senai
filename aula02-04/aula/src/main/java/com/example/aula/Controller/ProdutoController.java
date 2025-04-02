package com.example.aula.Controller;

import com.example.aula.DTO.ProdutoDTO;
import com.example.aula.Entity.Produto;
import com.example.aula.Repository.ProdutoRepository;
import com.example.aula.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> created(@RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO produto = produtoService.save(produtoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
}
