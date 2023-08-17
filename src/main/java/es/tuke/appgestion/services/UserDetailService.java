package es.tuke.appgestion.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.tuke.appgestion.repositories.UserRepository;
import es.tuke.appgestion.security.SecurityUser;

@Service 
public class UserDetailService implements UserDetailsService{
    
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        var optUser = this.userRepository.findByUsername(username);

        if(optUser.isPresent()){
            return new SecurityUser(optUser.get());
        }
        throw new UsernameNotFoundException("User not found: "+ username);

    }

}
