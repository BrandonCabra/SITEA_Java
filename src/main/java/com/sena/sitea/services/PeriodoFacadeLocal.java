/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Periodo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface PeriodoFacadeLocal {

    void create(Periodo periodo );

    void edit(Periodo periodo);

    void remove(Periodo periodo);

    Periodo find(Object id);

    List<Periodo> findAll();

    List<Periodo> findRange(int[] range);

    int count();
    
}
