package rs.raf.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.raf.demo.model.User;
import rs.raf.demo.requests.CreateUserRequest;
import rs.raf.demo.requests.UpdateUserRequest;
import rs.raf.demo.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUser){

        User user = new User();

        String password = passwordEncoder.encode(createUser.getPassword());
        user.setEmail(createUser.getEmail());
        user.setFirstName(createUser.getFirstName());
        user.setLastName(createUser.getLastName());
        user.setPassword(password);
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @GetMapping(value = "/del/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        Optional<User> user = Optional.ofNullable(userService.findbyID(id));
        if(user.isPresent())
        {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getbyID(@PathVariable("id") Long id){
        Optional<User> user = Optional.ofNullable(userService.findbyID(id));
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        else return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatebyID(@PathVariable("id")Long id, @RequestBody UpdateUserRequest updateUser){
        Optional<User> user = Optional.ofNullable(userService.findbyID(id));
        if(user.isPresent()){
            User usr = user.get();

            if(updateUser.getEmail()!=null)
                usr.setEmail(updateUser.getEmail());
            if(updateUser.getFirstName()!=null)
                usr.setEmail(updateUser.getFirstName());
            if(updateUser.getLastName()!=null)
                usr.setEmail(updateUser.getLastName());

            return new ResponseEntity<>(userService.save(usr), HttpStatus.OK);
        }
        else return ResponseEntity.notFound().build();
    }



}
