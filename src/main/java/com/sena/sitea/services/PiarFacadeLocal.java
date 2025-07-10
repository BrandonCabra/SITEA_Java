/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Piar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface PiarFacadeLocal {

    void create(Piar piar);

    void edit(Piar piar);

    void remove(Piar piar);

    Piar find(Object id);

    List<Piar> findAll();

    List<Piar> findRange(int[] range);

    int count();
    
}
