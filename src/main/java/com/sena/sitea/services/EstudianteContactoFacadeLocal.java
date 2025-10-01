/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.EstudianteContacto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface EstudianteContactoFacadeLocal {

    void create(EstudianteContacto estudianteContacto);

    void edit(EstudianteContacto estudianteContacto);

    void remove(EstudianteContacto estudianteContacto);

    EstudianteContacto find(Object id);

    List<EstudianteContacto> findAll();

    List<EstudianteContacto> findByEstudiante(Integer estudianteId);

    int count();
    
}
