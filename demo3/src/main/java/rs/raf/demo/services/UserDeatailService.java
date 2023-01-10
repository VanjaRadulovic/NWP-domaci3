package rs.raf.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.raf.demo.model.User;
import rs.raf.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserDeatailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDeatailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> usr = this.userRepository.findUserByEmail(username);
//
//        if(!usr.isPresent()) throw new UsernameNotFoundException("User not found in the database");
//
//        User user = usr.get();
//
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        user.getPermissions().forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getDescription())));
//        return  new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
//
        return null;
   }

}




