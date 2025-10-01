/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Area;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface AreaFacadeLocal {

    void create(Area area);

    void edit(Area area);

    void remove(Area area);

    Area find(Object id);

    List<Area> findAll();

    List<Area> findRange(int[] range);

    int count();
    
}
