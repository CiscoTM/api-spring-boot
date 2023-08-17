package es.tuke.appgestion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.tuke.appgestion.models.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    public Optional<User>findByUsername(String username);
}
