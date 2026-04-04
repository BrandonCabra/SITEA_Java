package com.sena.sitea.services;

import com.sena.sitea.entities.DimControlEntorno;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class DimControlEntornoFacade extends AbstractFacade<DimControlEntorno> implements DimControlEntornoFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DimControlEntornoFacade() {
        super(DimControlEntorno.class);
    }

    @Override
    public List<DimControlEntorno> findByCaracterizacion(Integer caracterizacionId) {
        return em.createNamedQuery("DimControlEntorno.findByCaracterizacion", DimControlEntorno.class)
                .setParameter("caracterizacionId", caracterizacionId)
                .getResultList();
    }
}
