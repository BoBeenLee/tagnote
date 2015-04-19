package kr.tagmemo.security;

import kr.tagmemo.user.User;
import kr.tagmemo.user.UserRepository;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new TagMemoUserDetails(user);
    }
}
