/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.GradoMateria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface GradoMateriaFacadeLocal {

    void create(GradoMateria gradoMateria);

    void edit(GradoMateria gradoMateria);

    void remove(GradoMateria gradoMateria);

    GradoMateria find(Object id);

    List<GradoMateria> findAll();

    List<GradoMateria> findRange(int[] range);

    int count();
    
}
