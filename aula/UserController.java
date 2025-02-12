package com.example.aula;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/user")
public class UserController {

    private List<User> users = new ArrayList<>();

    @GetMapping
    @Operation(description = "Retorna todos os usuários na lista")
    public List<User> getUser(){
        return users;
    }

    @GetMapping("/{id}")
    @Operation(description = "Retorna o usuário com base no id")
    public User getUserById(@PathVariable int id){
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @PostMapping
    @Operation(description = "Cria um usuário e adicionar na lista")
    public User createUser(@RequestBody User newUser){
        users.add(newUser);
        return newUser;
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualiza os dados do usuário identificado pelo ID")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id){
        User existeUser = users.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .orElse(null);

        if (existeUser == null) {
            return ResponseEntity.notFound().build();
        }

        existeUser.setNome(user.getNome());
        existeUser.setSobrenome(user.getSobrenome());

        return ResponseEntity.ok(existeUser);
        /*
        Entendendo a lógica:
            - O método PUT mapeado recebe um id do usuário na URL e os novos dados no corpo da requisição
            - Usamos o stream() para procurar o usuário que tem o id igual ao da URL. Se não encontrar, retornamos um erro 404 (ResponseEntity.notFound().build()).
            - Se o usuário existir, os campos são atualizados com os dados que vêm no corpo da requisição.
            - Retorna o objeto User atualizado e o status HTTP 200 OK.
         */
    }

}
