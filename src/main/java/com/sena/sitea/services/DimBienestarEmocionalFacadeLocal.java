package com.sena.sitea.services;

import com.sena.sitea.entities.DimBienestarEmocional;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DimBienestarEmocionalFacadeLocal {

    void create(DimBienestarEmocional dimBienestarEmocional);

    void edit(DimBienestarEmocional dimBienestarEmocional);

    void remove(DimBienestarEmocional dimBienestarEmocional);

    DimBienestarEmocional find(Object id);

    java.util.List<DimBienestarEmocional> findAll();

    java.util.List<DimBienestarEmocional> findRange(int[] range);

    int count();

    java.util.List<DimBienestarEmocional> findByCaracterizacion(Integer caracterizacionId);
}
