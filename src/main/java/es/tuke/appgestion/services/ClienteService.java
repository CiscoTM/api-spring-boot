package es.tuke.appgestion.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import es.tuke.appgestion.models.ClienteModel;
import es.tuke.appgestion.repositories.IClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    public ArrayList<ClienteModel> getAll(){
        return (ArrayList<ClienteModel>) clienteRepository.findAllActiveClientes();
    }

    public ArrayList<ClienteModel> getAllInactiveClientes(){
        return (ArrayList<ClienteModel>) clienteRepository.findAllInactiveClientes();
    }

    public Optional<ClienteModel> getById(Long id){
        
        if(Optional.of(clienteRepository.findById(id)).isPresent()){
            
            if(Optional.of(clienteRepository.findById(id)).get().get().getActive()==1){
                return clienteRepository.findById(id);
            
            }else {
            
                return Optional.empty();
        
            }
        } else {

            return Optional.empty();
        }        
        
    }

    

    public List<ClienteModel> getByName(String name){

        return clienteRepository.findByName(name);
        
    }

    public ClienteModel add(ClienteModel clienteModel){
        return clienteRepository.save(clienteModel);
    }

    public int delete(Long id){

        ClienteModel clienteModel = clienteRepository.findById(id).get();

        if(clienteModel!=null){
            clienteRepository.inactiveCliente(id);
            return 1;
        } else {
            return 0;
        }

    }

   
        
       
}
