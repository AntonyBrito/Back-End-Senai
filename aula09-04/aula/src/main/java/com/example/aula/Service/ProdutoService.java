package com.example.aula.Service;

import com.example.aula.DTO.ProdutoDTO;
import com.example.aula.Entity.Produto;
import com.example.aula.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Produto> getById(Long id) {
      return produtoRepository.findById(id);
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO){
        Produto produto = this.fromDTO(produtoDTO);
        Produto produtoBd = produtoRepository.save(produto);

        return this.toDTO(produtoBd);
    }

    public Optional<ProdutoDTO> updateProduto(Long id, ProdutoDTO produtoDTO){
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if(optionalProduto.isPresent()){
            Produto produto = optionalProduto.get();
            produto.setNome(produtoDTO.getNome());
            produto.setValor(produtoDTO.getValor());
            produto.setSaldo(produtoDTO.getSaldo());
            produto.setSaldoMinimo(produtoDTO.getSaldoMinimo());

            Produto produtoUpdate = produtoRepository.save(produto);

            return Optional.of(this.toDTO(produtoUpdate));
        }else {
            return Optional.empty();
        }
    }
    public boolean delete(Long id){
        if(produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
