package com.senai.Biblioteca.Repository;

import com.senai.Biblioteca.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
