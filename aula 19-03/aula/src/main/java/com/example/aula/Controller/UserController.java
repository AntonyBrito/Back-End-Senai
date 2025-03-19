package com.example.aula.Controller;

import com.example.aula.Entity.User;
import com.example.aula.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(users);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> userOption = userRepository.findById(id);

        if (userOption.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(userOption.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User userBd = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userBd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateById(@RequestBody User user, @PathVariable Long id) {
        Optional<User> userBd = userRepository.findById(id);

        if(!userBd.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }

        User userAtt = userBd.get();
        userAtt.setNome(user.getNome());
        userAtt.setEmail(user.getEmail());
        userRepository.save(userAtt);

        return ResponseEntity.status(HttpStatus.OK).body(userAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não enontrado");
        }
    }
}
