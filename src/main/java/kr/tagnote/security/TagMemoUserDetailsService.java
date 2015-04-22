package kr.tagnote.security;

import kr.tagnote.user.User;
import kr.tagnote.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Keeun Baik
 */
@Service
public class TagMemoUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        
        System.out.println("user : " + user);
        
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new TagMemoUserDetails(user);
    }
}
