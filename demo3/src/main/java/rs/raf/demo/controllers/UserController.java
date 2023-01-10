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
        user.setPermissions(createUser.getPermissions());
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @GetMapping(value = "/del/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        User user = (userService.findbyID(id));
        if(user!=null)
        {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getbyID(@PathVariable("id") Long id){
        User user = (userService.findbyID(id));
        if(user!=null) {
            return ResponseEntity.ok(user);
        }
        else return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatebyID(@PathVariable("id")Long id, @RequestBody UpdateUserRequest updateUser){
        User user= (userService.findbyID(id));
        if(user!=null){
            if(updateUser.getEmail()!=null)
                user.setEmail(updateUser.getEmail());
            if(updateUser.getFirstName()!=null)
                user.setEmail(updateUser.getFirstName());
            if(updateUser.getLastName()!=null)
                user.setEmail(updateUser.getLastName());
            if(updateUser.getPermissions()!=null)
                user.setPermissions(updateUser.getPermissions());

            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        }
        else return ResponseEntity.notFound().build();
    }



}
