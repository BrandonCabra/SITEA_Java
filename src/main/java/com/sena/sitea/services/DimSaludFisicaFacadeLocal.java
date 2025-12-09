package com.sena.sitea.services;

import com.sena.sitea.entities.DimSaludFisica;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DimSaludFisicaFacadeLocal {

    void create(DimSaludFisica dimSaludFisica);

    void edit(DimSaludFisica dimSaludFisica);

    void remove(DimSaludFisica dimSaludFisica);

    DimSaludFisica find(Object id);

    java.util.List<DimSaludFisica> findAll();

    java.util.List<DimSaludFisica> findRange(int[] range);

    int count();

    java.util.List<DimSaludFisica> findByCaracterizacion(Integer caracterizacionId);
}
