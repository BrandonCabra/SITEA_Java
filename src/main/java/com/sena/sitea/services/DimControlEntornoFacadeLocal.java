package com.sena.sitea.services;

import com.sena.sitea.entities.DimControlEntorno;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DimControlEntornoFacadeLocal {

    void create(DimControlEntorno dimControlEntorno);

    void edit(DimControlEntorno dimControlEntorno);

    void remove(DimControlEntorno dimControlEntorno);

    DimControlEntorno find(Object id);

    java.util.List<DimControlEntorno> findAll();

    java.util.List<DimControlEntorno> findRange(int[] range);

    int count();

    java.util.List<DimControlEntorno> findByCaracterizacion(Integer caracterizacionId);
}
