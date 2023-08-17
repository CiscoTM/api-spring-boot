package es.tuke.appgestion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.tuke.appgestion.models.Authority;
import es.tuke.appgestion.utils.AuthorityName;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    
    Optional<Authority> findByName(AuthorityName name);

}
