package com.sena.sitea.services;

import com.sena.sitea.entities.DimPedagogica;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DimPedagogicaFacadeLocal {

    void create(DimPedagogica dimPedagogica);

    void edit(DimPedagogica dimPedagogica);

    void remove(DimPedagogica dimPedagogica);

    DimPedagogica find(Object id);

    java.util.List<DimPedagogica> findAll();

    java.util.List<DimPedagogica> findRange(int[] range);

    int count();

    java.util.List<DimPedagogica> findByCaracterizacion(Integer caracterizacionId);
}
