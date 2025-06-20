/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Trastorno;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface TrastornoFacadeLocal {

    void create(Trastorno trastorno);

    void edit(Trastorno trastorno);

    void remove(Trastorno trastorno);

    Trastorno find(Object id);

    List<Trastorno> findAll();

    List<Trastorno> findRange(int[] range);

    int count();
    
}
