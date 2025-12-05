package com.sena.sitea.services;

import com.sena.sitea.entities.ObservacionSistematica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SITEA
 */
@Local
public interface ObservacionSistematicaFacadeLocal {

    void create(ObservacionSistematica observacionSistematica);

    void edit(ObservacionSistematica observacionSistematica);

    void remove(ObservacionSistematica observacionSistematica);

    ObservacionSistematica find(Object id);

    List<ObservacionSistematica> findAll();

    List<ObservacionSistematica> findRange(int[] range);

    int count();
    
    List<ObservacionSistematica> findByCaracterizacion(Integer caracterizacionId);
}
