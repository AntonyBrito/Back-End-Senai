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
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> getById(@PathVariable Long id){
        Optional<Produto> optionalProduto = produtoService.getById(id);
        if (optionalProduto.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalProduto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> created(@RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO produto = produtoService.save(produtoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
        Optional<ProdutoDTO> produtoDTOOptional= produtoService.updateProduto(id, produtoDTO);
        if(produtoDTOOptional.isPresent()){
            return ResponseEntity.ok(produtoDTOOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(produtoService.delete(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
