package com.sena.sitea.services;

import com.sena.sitea.entities.ValoracionRespuesta;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ValoracionRespuestaFacadeLocal {
    void create(ValoracionRespuesta r);
    void edit(ValoracionRespuesta r);
    void remove(ValoracionRespuesta r);
    ValoracionRespuesta find(Object id);
    List<ValoracionRespuesta> findAll();
}
