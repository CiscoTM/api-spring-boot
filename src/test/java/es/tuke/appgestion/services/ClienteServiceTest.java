package es.tuke.appgestion.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import es.tuke.appgestion.models.ClienteModel;
import es.tuke.appgestion.repositories.IClienteRepository;

public class ClienteServiceTest {
    
    @Mock
    private IClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private ClienteModel clienteModel;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);

        clienteModel = new ClienteModel();
        clienteModel.setActive(1);
        clienteModel.setDataBase("db");
        clienteModel.setName("prueba");
        clienteModel.setPass("pass");
        clienteModel.setId(Long.valueOf(1));


    }
    
    @Test
    void testAdd() {

    }

    @Test
    void testGetAll() {

        ArrayList<ClienteModel> lista = new ArrayList<ClienteModel>();

        when(repository
        .findAllActiveClientes())
        .thenReturn(lista);

        assertNotNull(service.getAll());

    }

   

    @Test
    void testGetById() {

    }

    @Test
    void testGetByName() {

    }
}
