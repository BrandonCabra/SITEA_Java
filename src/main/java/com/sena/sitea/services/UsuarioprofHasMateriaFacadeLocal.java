/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.UsuarioprofHasMateria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface UsuarioprofHasMateriaFacadeLocal {

    void create(UsuarioprofHasMateria usuarioprofHasMateria);

    void edit(UsuarioprofHasMateria usuarioprofHasMateria);

    void remove(UsuarioprofHasMateria usuarioprofHasMateria);

    UsuarioprofHasMateria find(Object id);

    List<UsuarioprofHasMateria> findAll();

    List<UsuarioprofHasMateria> findRange(int[] range);

    int count();
    
}
