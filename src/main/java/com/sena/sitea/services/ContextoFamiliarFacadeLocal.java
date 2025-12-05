package com.sena.sitea.services;

import com.sena.sitea.entities.ContextoFamiliar;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaz de servicio local para la entidad ContextoFamiliar
 * 
 * @author SITEA
 */
@Local
public interface ContextoFamiliarFacadeLocal {

    void create(ContextoFamiliar contextoFamiliar);

    void edit(ContextoFamiliar contextoFamiliar);

    void remove(ContextoFamiliar contextoFamiliar);

    ContextoFamiliar find(Object id);

    List<ContextoFamiliar> findAll();

    List<ContextoFamiliar> findRange(int[] range);

    int count();
    
    List<ContextoFamiliar> findByCaracterizacion(Integer idCaracterizacion);
}
