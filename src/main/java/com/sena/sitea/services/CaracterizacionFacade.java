/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Caracterizacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bjcab
 */
@Stateless
public class CaracterizacionFacade extends AbstractFacade<Caracterizacion> implements CaracterizacionFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaracterizacionFacade() {
        super(Caracterizacion.class);
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> contarPorDiagnostico() {
        String jpql = "SELECT c.diagnostico, COUNT(c) "
                    + "FROM Caracterizacion c "
                    + "GROUP BY c.diagnostico";
        return em.createQuery(jpql).getResultList();
    }

    
}
