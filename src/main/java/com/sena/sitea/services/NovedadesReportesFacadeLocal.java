/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.NovedadesReportes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface NovedadesReportesFacadeLocal {

    void create(NovedadesReportes novedadesReportes);

    void edit(NovedadesReportes novedadesReportes);

    void remove(NovedadesReportes novedadesReportes);

    NovedadesReportes find(Object id);

    List<NovedadesReportes> findAll();

    List<NovedadesReportes> findRange(int[] range);

    int count();
    
}
