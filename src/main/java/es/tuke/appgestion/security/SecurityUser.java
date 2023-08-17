package es.tuke.appgestion.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import es.tuke.appgestion.models.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private User user;
    
    @Override
    public String getUsername() {
        
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        
        return user.getPassword();
    }

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    
        return user
            .getAuthorities()
            .stream()
            .map(SecurityAuthority::new)
            .toList();

    }

    

   

    @Override
    public boolean isAccountNonExpired() {
        
        return true;    
    }

    @Override
    public boolean isAccountNonLocked() {
        
        return true;
    
    }

    @Override
    public boolean isCredentialsNonExpired() {
    
        return true;

    }

    @Override
    public boolean isEnabled() {
    
        return true;

    }
    
}
