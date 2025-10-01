/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.FechaRegistro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface FechaRegistroFacadeLocal {

    void create(FechaRegistro fechaRegistro);

    void edit(FechaRegistro fechaRegistro);

    void remove(FechaRegistro fechaRegistro);

    FechaRegistro find(Object id);

    List<FechaRegistro> findAll();

    List<FechaRegistro> findRange(int[] range);

    int count();
    
}
