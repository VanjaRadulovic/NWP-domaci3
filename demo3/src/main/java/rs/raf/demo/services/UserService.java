package rs.raf.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import rs.raf.demo.model.User;
import rs.raf.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

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
       // User myUser = this.findByUsername(username);
//        if(myUser == null) {
//            throw new UsernameNotFoundException("User name "+username+" not found");
//        }

        return null;
        //new org.springframework.security.core.userdetails.User(myUser.getUsername(), myUser.getPassword(), new ArrayList<>());
    }


    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Object save(User user){
        return this.userRepository.save(user);
    }

    public void delete(long id){
        this.userRepository.deleteById(id);
    }

    public User findbyID(long id){
        return this.userRepository.getById(id);
    }


}
