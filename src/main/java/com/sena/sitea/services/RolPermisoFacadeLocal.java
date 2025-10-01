/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.RolPermiso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface RolPermisoFacadeLocal {

    void create(RolPermiso rolPermiso);

    void edit(RolPermiso rolPermiso);

    void remove(RolPermiso rolPermiso);

    RolPermiso find(Object id);

    List<RolPermiso> findAll();

    List<RolPermiso> findRange(int[] range);

    int count();
    
}
