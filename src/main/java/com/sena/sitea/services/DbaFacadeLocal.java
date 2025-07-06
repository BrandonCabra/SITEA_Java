/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Dba;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface DbaFacadeLocal {

    void create(Dba dba);

    void edit(Dba dba);

    void remove(Dba dba);

    Dba find(Object id);

    List<Dba> findAll();

    List<Dba> findRange(int[] range);

    int count();
    
}
