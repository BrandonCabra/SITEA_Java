/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.AuditEstudiante;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author brandon
 */
@Local
public interface AuditEstudianteFacadeLocal {

    void create(AuditEstudiante auditEstudiante);

    void edit(AuditEstudiante auditEstudiante);

    void remove(AuditEstudiante auditEstudiante);

    AuditEstudiante find(Object id);

    List<AuditEstudiante> findAll();

    List<AuditEstudiante> findRange(int[] range);

    int count();
    
}
