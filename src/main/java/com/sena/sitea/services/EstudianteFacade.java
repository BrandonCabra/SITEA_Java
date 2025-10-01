/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Estudiante;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bjcab
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> implements EstudianteFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    @Override
       
    public Estudiante findByDocumento(Integer idTipoDocumento, String numeroDocumento) {
    try {
        return getEntityManager()
                .createNamedQuery("Estudiante.findByDocumento", Estudiante.class)
                .setParameter("idTipoDocumento", idTipoDocumento)
                .setParameter("numeroDocumento", numeroDocumento)
                .getSingleResult();
    } catch (NoResultException e) {
        return null; // No existe estudiante con esos datos
    }
}



    @Override
    public int countByYear(int year) {
        try {
            Long count = em.createNamedQuery("Estudiante.countByYear", Long.class)
                          .setParameter("year", year)
                          .getSingleResult();
            return count.intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Estudiante> findByEstadoRegistro(String estado) {
        return em.createQuery("SELECT e FROM Estudiante e WHERE e.estadoRegistro = :estado", Estudiante.class)
                 .setParameter("estado", estado)
                 .getResultList();
    }

    @Override
    public List<Estudiante> findByExpedienteId(String expedienteId) {
        return em.createQuery("SELECT e FROM Estudiante e WHERE e.expedienteId = :expediente", Estudiante.class)
                 .setParameter("expediente", expedienteId)
                 .getResultList();
    }

    @Override
    public void updateExpedienteId(Integer estudianteId, String nuevoExpediente) {
    }

   

    
    
    
    
}
