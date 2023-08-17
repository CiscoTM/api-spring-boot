package es.tuke.appgestion.security;

import org.springframework.security.core.GrantedAuthority;

import es.tuke.appgestion.models.Authority;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private final Authority authority;

    @Override
    public String getAuthority() {
    
        return authority.getName().toString();

    }
    
}
