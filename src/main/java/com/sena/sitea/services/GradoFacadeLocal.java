/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Grado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface GradoFacadeLocal {

    void create(Grado grado);

    void edit(Grado grado);

    void remove(Grado grado);

    Grado find(Object id);

    List<Grado> findAll();

    List<Grado> findRange(int[] range);

    int count();
    
}
