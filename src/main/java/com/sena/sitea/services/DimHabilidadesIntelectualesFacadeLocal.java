package com.sena.sitea.services;

import com.sena.sitea.entities.DimHabilidadesIntelectuales;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DimHabilidadesIntelectualesFacadeLocal {

    void create(DimHabilidadesIntelectuales dimHabilidadesIntelectuales);

    void edit(DimHabilidadesIntelectuales dimHabilidadesIntelectuales);

    void remove(DimHabilidadesIntelectuales dimHabilidadesIntelectuales);

    DimHabilidadesIntelectuales find(Object id);

    java.util.List<DimHabilidadesIntelectuales> findAll();

    java.util.List<DimHabilidadesIntelectuales> findRange(int[] range);

    int count();

    java.util.List<DimHabilidadesIntelectuales> findByCaracterizacion(Integer caracterizacionId);
}
