package com.mau.spring.repository;


import com.mau.spring.model.entity.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento,Integer> { //un repository es una abstraccion de la capa de base de datos (una IPersistencia)

    List<Alimento> findByNombre(String nombre); //se hace automaticamente solo por que el nombre del metodo es findByName, entonces hace un SELECT FROM WHERE nombre = nombre

//    @Query(value = "Select * from alimento where weight = ?1", nativeQuery = true)
//    List<Alimento> findByWeight(int weight);


}
