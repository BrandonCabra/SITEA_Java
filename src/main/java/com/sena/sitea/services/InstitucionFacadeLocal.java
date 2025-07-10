/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Institucion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface InstitucionFacadeLocal {

    void create(Institucion institucion);

    void edit(Institucion institucion);

    void remove(Institucion institucion);

    Institucion find(Object id);

    List<Institucion> findAll();

    List<Institucion> findRange(int[] range);

    int count();
    
}
