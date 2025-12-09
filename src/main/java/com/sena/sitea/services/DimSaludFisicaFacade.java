package com.sena.sitea.services;

import com.sena.sitea.entities.DimSaludFisica;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DimSaludFisicaFacade extends AbstractFacade<DimSaludFisica> implements DimSaludFisicaFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DimSaludFisicaFacade() {
        super(DimSaludFisica.class);
    }

    @Override
    public List<DimSaludFisica> findByCaracterizacion(Integer caracterizacionId) {
        return em.createNamedQuery("DimSaludFisica.findByCaracterizacion", DimSaludFisica.class)
                .setParameter("caracterizacionId", caracterizacionId)
                .getResultList();
    }
}
