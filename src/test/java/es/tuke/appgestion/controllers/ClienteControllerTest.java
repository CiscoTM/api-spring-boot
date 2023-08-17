package es.tuke.appgestion.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import es.tuke.appgestion.models.ClienteModel;
import es.tuke.appgestion.services.ClienteService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private ClienteModel cliente;

    private ArrayList<ClienteModel> listaClientes;
    
    @BeforeEach
    public void setup(){

        cliente = new ClienteModel();
        cliente.setId(Long.valueOf(1));
        cliente.setName("John");
        cliente.setActive(1);
        cliente.setPass("Doe");
        cliente.setDataBase("void");            

        listaClientes = new ArrayList<ClienteModel>();

        listaClientes.add(cliente);

    }

    @Test //ArrayList<ClienteModel>getAll()
    public void getAllClientesTest() throws Exception{
        
        ArrayList<ClienteModel> clientes = new ArrayList<ClienteModel>();
        clientes.add(cliente);

        Mockito
        .when(clienteService.getAll()).thenReturn(clientes);

        mockMvc
        .perform(get("/api/clientes")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(clientes.get(0).getName()));   

    }

    @Test //ArrayList<ClienteModel>getInactives()
    public void getInactivesClientesTest() throws Exception{

        ArrayList<ClienteModel> clientes = new ArrayList<ClienteModel>();
        cliente.setActive(0);
        clientes.add(cliente);

        Mockito.when(clienteService.getAllInactiveClientes()).thenReturn(clientes);

        mockMvc.perform(get("/api/clientes/inactivos")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(clientes.get(0).getName()));
        
        
    }

    @Test // ClienteModel getById(@PathVariable("id") Long id)
    public void getClienteByIdTest() throws Exception{

        Optional<ClienteModel> oCliente = Optional.of(cliente);

        Mockito
        .when(clienteService
        .getById
            (
                Long.valueOf(1))
            )
            .thenReturn(oCliente);


        mockMvc
        .perform(get("/api/clientes/id/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(oCliente.get().getName()));   
        
    }

    @Test //ClienteModel add(@RequestBody ClienteModel cliente)
    public void saveClienteTest() throws Exception{

        ClienteModel postCliente = new ClienteModel();
        postCliente.setName("John");
        postCliente.setActive(1);
        postCliente.setPass("Doe");
        postCliente.setDataBase("void");

        Mockito.when(clienteService.add(postCliente)).thenReturn(cliente);

        mockMvc.perform(post("/api/add").contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                        "    \"name\":\"John\",\n" +
                        "    \"active\": \"1\",\n" +
                        "    \"pass\":\"Doe\",\n" +
                        "    \"database\":\"void\"\n" +
                        "}"))
                .andExpect(status().isCreated());
    }

    @Test //ClienteModel getByname(@PathVariable("name") String name)
    public void getClienteByNameTest(){
        
    }

    @Test //int delete(@PathVariable("id") Long id)
    public void deleteClienteTest(){
        
    }

}
