package com.sena.sitea.services;

import com.sena.sitea.entities.DimPedagogica;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DimPedagogicaFacade extends AbstractFacade<DimPedagogica> implements DimPedagogicaFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DimPedagogicaFacade() {
        super(DimPedagogica.class);
    }

    @Override
    public List<DimPedagogica> findByCaracterizacion(Integer caracterizacionId) {
        return em.createNamedQuery("DimPedagogica.findByCaracterizacion", DimPedagogica.class)
                .setParameter("caracterizacionId", caracterizacionId)
                .getResultList();
    }
}
