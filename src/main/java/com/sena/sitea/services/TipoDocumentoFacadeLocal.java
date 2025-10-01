/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.TipoDocumento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface TipoDocumentoFacadeLocal {

    void create(TipoDocumento tipoDocumento);

    void edit(TipoDocumento tipoDocumento);

    void remove(TipoDocumento tipoDocumento);

    TipoDocumento find(Object id);

    List<TipoDocumento> findAll();

    List<TipoDocumento> findRange(int[] range);

    int count();
    
}
