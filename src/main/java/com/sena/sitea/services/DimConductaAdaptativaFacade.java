package com.sena.sitea.services;

import com.sena.sitea.entities.DimConductaAdaptativa;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class DimConductaAdaptativaFacade extends AbstractFacade<DimConductaAdaptativa> implements DimConductaAdaptativaFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DimConductaAdaptativaFacade() {
        super(DimConductaAdaptativa.class);
    }

    @Override
    public List<DimConductaAdaptativa> findByCaracterizacion(Integer caracterizacionId) {
        return em.createNamedQuery("DimConductaAdaptativa.findByCaracterizacion", DimConductaAdaptativa.class)
                .setParameter("caracterizacionId", caracterizacionId)
                .getResultList();
    }
}
