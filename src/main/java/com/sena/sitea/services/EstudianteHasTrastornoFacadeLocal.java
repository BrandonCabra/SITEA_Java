/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.EstudianteHasTrastorno;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface EstudianteHasTrastornoFacadeLocal {

    void create(EstudianteHasTrastorno estudianteHasTrastorno);

    void edit(EstudianteHasTrastorno estudianteHasTrastorno);

    void remove(EstudianteHasTrastorno estudianteHasTrastorno);

    EstudianteHasTrastorno find(Object id);

    List<EstudianteHasTrastorno> findAll();

    List<EstudianteHasTrastorno> findRange(int[] range);

    int count();
    
}
