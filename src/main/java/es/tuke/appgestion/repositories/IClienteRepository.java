package es.tuke.appgestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.tuke.appgestion.models.ClienteModel;

import java.util.ArrayList;
import java.util.Optional;


public interface IClienteRepository extends JpaRepository<ClienteModel,Long > {

    Optional<ClienteModel> findByName(String name);

    // Obtiene listado completo clientes activos
    @Query("select u from ClienteModel u where u.active = 1")
    public ArrayList<ClienteModel>findAllActiveClientes();

     // Obtiene listado completo clientes activos
    @Query("select u from ClienteModel u where u.active = 0")
    public ArrayList<ClienteModel>findAllInactiveClientes();


    // Pasa el estado de un cliente a inactivo
    @Modifying
    @Transactional
    @Query("update ClienteModel u set u.active = 0 where u.id = :id")
    public int inactiveCliente(@Param("id")Long id);


}


