package com.sena.sitea.services;

import com.sena.sitea.entities.DimConductaAdaptativa;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DimConductaAdaptativaFacadeLocal {

    void create(DimConductaAdaptativa dimConductaAdaptativa);

    void edit(DimConductaAdaptativa dimConductaAdaptativa);

    void remove(DimConductaAdaptativa dimConductaAdaptativa);

    DimConductaAdaptativa find(Object id);

    java.util.List<DimConductaAdaptativa> findAll();

    java.util.List<DimConductaAdaptativa> findRange(int[] range);

    int count();

    java.util.List<DimConductaAdaptativa> findByCaracterizacion(Integer caracterizacionId);
}
