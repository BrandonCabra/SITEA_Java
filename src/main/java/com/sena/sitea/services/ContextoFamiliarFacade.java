package com.sena.sitea.services;

import com.sena.sitea.entities.ContextoFamiliar;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 * Implementación del servicio de ContextoFamiliar
 * Extiende AbstractFacade para operaciones CRUD básicas
 * 
 * @author SITEA
 */
@Stateless
public class ContextoFamiliarFacade extends AbstractFacade<ContextoFamiliar> implements ContextoFamiliarFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContextoFamiliarFacade() {
        super(ContextoFamiliar.class);
    }

    /**
     * Buscar contexto familiar por ID de caracterización
     * 
     * @param idCaracterizacion ID de la caracterización
     * @return Lista de contextos familiares asociados (típicamente 0 o 1)
     */
    @Override
    public List<ContextoFamiliar> findByCaracterizacion(Integer idCaracterizacion) {
        Query q = em.createNamedQuery("ContextoFamiliar.findByCaracterizacion");
        q.setParameter("idCaracterizacion", idCaracterizacion);
        return q.getResultList();
    }
}
