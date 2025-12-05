package com.sena.sitea.services;

import com.sena.sitea.entities.DimensionValoracion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SITEA
 */
@Local
public interface DimensionValoracionFacadeLocal {

    void create(DimensionValoracion dimensionValoracion);

    void edit(DimensionValoracion dimensionValoracion);

    void remove(DimensionValoracion dimensionValoracion);

    DimensionValoracion find(Object id);

    List<DimensionValoracion> findAll();

    List<DimensionValoracion> findRange(int[] range);

    int count();
    
    List<DimensionValoracion> findByCaracterizacion(Integer caracterizacionId);
}
