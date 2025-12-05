package com.sena.sitea.services;

import com.sena.sitea.entities.ContextoEscolar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ContextoEscolarFacade extends AbstractFacade<ContextoEscolar> implements ContextoEscolarFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContextoEscolarFacade() {
        super(ContextoEscolar.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ContextoEscolar> findByCaracterizacion(Integer idCaracterizacion) {
        try {
            return em.createQuery("SELECT c FROM ContextoEscolar c WHERE c.caracterizacion.idCaracterizacion = :id", ContextoEscolar.class)
                    .setParameter("id", idCaracterizacion)
                    .getResultList();
        } catch (Exception e) {
            return java.util.Collections.emptyList();
        }
    }
}
