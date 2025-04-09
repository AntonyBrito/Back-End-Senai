package com.example.aula.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private String nome;
    private Double valor;
    private int saldo;
    private int saldoMinimo;
}
