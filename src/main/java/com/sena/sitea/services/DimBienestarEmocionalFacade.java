package com.sena.sitea.services;

import com.sena.sitea.entities.DimBienestarEmocional;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DimBienestarEmocionalFacade extends AbstractFacade<DimBienestarEmocional> implements DimBienestarEmocionalFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DimBienestarEmocionalFacade() {
        super(DimBienestarEmocional.class);
    }

    @Override
    public List<DimBienestarEmocional> findByCaracterizacion(Integer caracterizacionId) {
        return em.createNamedQuery("DimBienestarEmocional.findByCaracterizacion", DimBienestarEmocional.class)
                .setParameter("caracterizacionId", caracterizacionId)
                .getResultList();
    }
}
