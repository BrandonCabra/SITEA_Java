package com.sena.sitea.services;

import com.sena.sitea.entities.DimHabilidadesIntelectuales;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DimHabilidadesIntelectualesFacade extends AbstractFacade<DimHabilidadesIntelectuales> implements DimHabilidadesIntelectualesFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DimHabilidadesIntelectualesFacade() {
        super(DimHabilidadesIntelectuales.class);
    }

    @Override
    public List<DimHabilidadesIntelectuales> findByCaracterizacion(Integer caracterizacionId) {
        return em.createNamedQuery("DimHabilidadesIntelectuales.findByCaracterizacion", DimHabilidadesIntelectuales.class)
                .setParameter("caracterizacionId", caracterizacionId)
                .getResultList();
    }
}
