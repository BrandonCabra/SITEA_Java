package com.sena.sitea.services;

import com.sena.sitea.entities.ValoracionInstrumento;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ValoracionInstrumentoFacadeLocal {
    void create(ValoracionInstrumento v);
    void edit(ValoracionInstrumento v);
    void remove(ValoracionInstrumento v);
    ValoracionInstrumento find(Object id);
    List<ValoracionInstrumento> findAll();
}
