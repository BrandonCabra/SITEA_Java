/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Caracterizacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface CaracterizacionFacadeLocal {

    void create(Caracterizacion caracterizacion);

    void edit(Caracterizacion caracterizacion);

    void remove(Caracterizacion caracterizacion);

    Caracterizacion find(Object id);

    List<Caracterizacion> findAll();

    List<Caracterizacion> findRange(int[] range);

    int count();
    
}
