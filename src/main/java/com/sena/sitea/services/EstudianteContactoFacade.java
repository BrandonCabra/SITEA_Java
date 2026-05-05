/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.EstudianteContacto;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author bjcab
 */
@Stateless
public class EstudianteContactoFacade extends AbstractFacade<EstudianteContacto> implements EstudianteContactoFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteContactoFacade() {
        super(EstudianteContacto.class);
    }

    @Override
    public List<EstudianteContacto> findByEstudiante(Integer estudianteId) {
        return em.createNamedQuery("EstudianteContacto.findByEstudiante", EstudianteContacto.class)
                 .setParameter("estudianteId", estudianteId)
                 .getResultList();
    }
    
}
