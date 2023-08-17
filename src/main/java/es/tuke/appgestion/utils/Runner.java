package es.tuke.appgestion.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import es.tuke.appgestion.models.Authority;
import es.tuke.appgestion.models.User;
import es.tuke.appgestion.repositories.AuthorityRepository;
import es.tuke.appgestion.repositories.UserRepository;

@Component
public class Runner implements CommandLineRunner {

    private final UserRepository UserRepository;
    private final AuthorityRepository authorityRepository;

    public Runner (
        UserRepository UserRepository,
        AuthorityRepository authorityRepository)
    {
        this.UserRepository=UserRepository;
        this.authorityRepository=authorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.authorityRepository.count() == 0){
            
            this.authorityRepository.saveAll(
                List.of(
                    new Authority(AuthorityName.ADMIN),
                    new Authority(AuthorityName.READ),
                    new Authority(AuthorityName.WRITE)
                    )
                );
        }
        
        if(this.UserRepository.count() == 0){
            
            var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            this.UserRepository.saveAll(
                List.of(
                    new User(
                        "Fran",
                        encoders.encode("@42710Nn"),
                        List.of(this.authorityRepository.findByName(AuthorityName.ADMIN).get())
                    ),
                    new User(
                        "bartolo",
                        encoders.encode("04271022"),
                        List.of(this.authorityRepository.findByName(AuthorityName.READ).get())
                    ),
                    new User(
                        "caparanas",
                        encoders.encode("042710zz"),
                        List.of(this.authorityRepository.findByName(AuthorityName.WRITE).get())
                    )
                    )
                );
        } 
    }
     
}
