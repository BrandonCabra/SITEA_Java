/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.UsuarioRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface UsuarioRolFacadeLocal {

    void create(UsuarioRol usuarioRol);

    void edit(UsuarioRol usuarioRol);

    void remove(UsuarioRol usuarioRol);

    UsuarioRol find(Object id);

    List<UsuarioRol> findAll();

    List<UsuarioRol> findRange(int[] range);

    int count();
    
}
