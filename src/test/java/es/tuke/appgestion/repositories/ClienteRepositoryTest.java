package es.tuke.appgestion.repositories;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import es.tuke.appgestion.models.ClienteModel;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
//import java.util.Optional;


@DataJpaTest
public class ClienteRepositoryTest {
    
    @Autowired IClienteRepository repositoryTest;

    @BeforeEach
    void setup(){

        repositoryTest.saveAllAndFlush(List.of(
        new ClienteModel(1L, "Bartolo", "Gutierrez", "BdGb", 1),
        new ClienteModel(2L, "Bernardo", "Gomez", "BdGb2", 0)
        ));

    }

    @AfterEach
    void setDown(){

        repositoryTest.deleteAll();
    }

     @Test
    void findAllClientesFound(){

        List<ClienteModel>lista = repositoryTest.findAll();

        assertThat(lista).hasSize(2);

    }

    @Test
    void findAllActiveClientesFound(){

        List<ClienteModel>lista = repositoryTest.findAllActiveClientes();

        assertThat(lista).hasSize(1);

    }

    @Test
    void findAllInactiveClientesFound(){

        List<ClienteModel>lista = repositoryTest.findAllInactiveClientes();

        assertThat(lista).hasSize(1);

    }

    @Test 
    void findClientesByNameFound(){

        List<ClienteModel>lista = repositoryTest.findByName("Juan");

        assertThat(lista).hasSize(0);

    }

    @Test 
    void inactiveClienteByIdFound(){

        int num = repositoryTest.inactiveCliente(2L);

        boolean resultado = (num == 1) ? true : false;

        assertThat(resultado).isEqualTo(true);

    }

}
