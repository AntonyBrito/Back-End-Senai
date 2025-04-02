package com.example.aula.Service;

import com.example.aula.DTO.ProdutoDTO;
import com.example.aula.Entity.Produto;
import com.example.aula.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto fromDTO(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());
        produto.setSaldo(produtoDTO.getSaldo());
        produto.setSaldoMinimo(produtoDTO.getSaldoMinimo());

        return produto;
    }

    public ProdutoDTO toDTO(Produto produto){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setValor(produto.getValor());
        produtoDTO.setSaldo(produto.getSaldo());
        produtoDTO.setSaldoMinimo(produto.getSaldoMinimo());

        return produtoDTO;
    }

    public List<Produto> getAll(){
        return produtoRepository.findAll();
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO){
        Produto produto = this.fromDTO(produtoDTO);
        Produto produtoBd = produtoRepository.save(produto);

        return this.toDTO(produtoBd);
    }
}
