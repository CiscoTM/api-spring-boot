package es.tuke.appgestion.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.tuke.appgestion.models.ClienteModel;
import es.tuke.appgestion.services.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/clientes")
    public ArrayList<ClienteModel>getAll(){

        return service.getAll();

    }

    @GetMapping("clientes/inactivos")
    public ArrayList<ClienteModel>getInactives(){

        return service.getAllInactiveClientes();

    }

    @GetMapping("clientes/id/{id}")
    public ClienteModel getById(@PathVariable("id") Long id){

        
        return service.getById(id)
                        .orElse(null);
 
    }

    @PostMapping("/add")
    public ClienteModel add(@RequestBody ClienteModel cliente){

        return service.add(cliente);

    }

    @GetMapping("clientes/name/{name}")
    public ClienteModel getByname(@PathVariable("name") String name){

        return service.getByName(name)
                        .orElse(null);
    //    return service.getById(id)
    //                     .orElse(new ClienteModel());
    }

    
    @PutMapping("clientes/delete/{id}")
        public int delete(@PathVariable("id") Long id){

            return service.delete(id);

    }

    
}
