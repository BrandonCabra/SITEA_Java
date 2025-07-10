/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.RepresentanteLegal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface RepresentanteLegalFacadeLocal {

    void create(RepresentanteLegal representanteLegal);

    void edit(RepresentanteLegal representanteLegal);

    void remove(RepresentanteLegal representanteLegal);

    RepresentanteLegal find(Object id);

    List<RepresentanteLegal> findAll();

    List<RepresentanteLegal> findRange(int[] range);

    int count();
    
}
