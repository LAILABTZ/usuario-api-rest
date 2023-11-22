package com.acme.usuario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acme.usuario.exception.ResourceNotFoundException;
import com.acme.usuario.model.User;
import com.acme.usuario.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

  public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

  // @Autowired
  private UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @ResponseBody
  @RequestMapping(path = "/hello")
  public String sayHello() {
    return HELLO_TEXT;
  }

  @PostMapping("/saveUser")
  public ResponseEntity<String> saveUser(@RequestBody User user) {
    userRepository.save(user);
    return ResponseEntity.ok(user.toString());
  }

  @GetMapping("/getUsers")
  public ResponseEntity<List<User>> getUsers() {
    List<User> users = userRepository.findAll();
    return ResponseEntity.ok(users);
  }

  @PutMapping("/updateUser/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
   User user = userRepository.findById(id)
     .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
  
   user.setName(userDetails.getName());
   user.setEmail(userDetails.getEmail());
  
   User updatedUser = userRepository.save(user);
   return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/deleteUser/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
