/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.ActividadClase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface ActividadClaseFacadeLocal {

    void create(ActividadClase actividadClase);

    void edit(ActividadClase actividadClase);

    void remove(ActividadClase actividadClase);

    ActividadClase find(Object id);

    List<ActividadClase> findAll();

    List<ActividadClase> findRange(int[] range);

    int count();
    
}
