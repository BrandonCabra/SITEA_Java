/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.BoletinAcademico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface BoletinAcademicoFacadeLocal {

    void create(BoletinAcademico boletinAcademico);

    void edit(BoletinAcademico boletinAcademico);

    void remove(BoletinAcademico boletinAcademico);

    BoletinAcademico find(Object id);

    List<BoletinAcademico> findAll();

    List<BoletinAcademico> findRange(int[] range);

    int count();
    
}
