package com.sena.sitea.services;

import com.sena.sitea.entities.ContextoEscolar;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ContextoEscolarFacadeLocal {
    void create(ContextoEscolar contextoEscolar);
    void edit(ContextoEscolar contextoEscolar);
    void remove(ContextoEscolar contextoEscolar);
    ContextoEscolar find(Object id);
    List<ContextoEscolar> findAll();
    List<ContextoEscolar> findByCaracterizacion(Integer idCaracterizacion);
}
