package rs.raf.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.raf.demo.model.User;
import rs.raf.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;
    private TaskScheduler taskScheduler;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, TaskScheduler taskScheduler) {
        this.passwordEncoder = passwordEncoder;

        this.userRepository = userRepository;
        this.taskScheduler = taskScheduler;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usr = this.userRepository.findUserByEmail(username);
        if(usr == null) {
            throw new UsernameNotFoundException("User name "+username+" not found");
        }

        return new org.springframework.security.core.userdetails.User(usr.get().getEmail(), usr.get().getPassword(), new ArrayList<>());
    }




    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Object save(User user){
        return this.userRepository.save(user);
    }

    public void delete(Long id){
        this.userRepository.deleteById(id);
    }

    public Optional<User> findbyID(Long id){
        return this.userRepository.findById(id);
    }


    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
