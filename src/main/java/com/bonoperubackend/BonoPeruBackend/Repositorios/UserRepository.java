package com.bonoperubackend.BonoPeruBackend.Repositorios;

import java.util.List;
import java.util.Optional;

//import com.bonoperubackend.BonoPeruBackend.Modelos.User;
import com.bonoperubackend.BonoPeruBackend.Modelos.Horario;
import com.bonoperubackend.BonoPeruBackend.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsuario(String username);

    Boolean existsByUsuario(String username);

    @Query(value="select * from usuario where estado = 'ACT'",nativeQuery=true)
    List<Usuario> findAllACTUsuarios();

    @Query(value="select * from usuario where estado = 'ACT' and (nombres like %?1% or apellidos like %?1%) ",nativeQuery=true)
    List<Usuario> findByNombresApellidosACT(String busqueda);
}
