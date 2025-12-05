package com.sena.sitea.services;

import com.sena.sitea.entities.ObservacionSistematica;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SITEA
 */
@Stateless
public class ObservacionSistematicaFacade extends AbstractFacade<ObservacionSistematica> implements ObservacionSistematicaFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObservacionSistematicaFacade() {
        super(ObservacionSistematica.class);
    }
    
    @Override
    public List<ObservacionSistematica> findByCaracterizacion(Integer caracterizacionId) {
        return em.createNamedQuery("ObservacionSistematica.findByCaracterizacion", ObservacionSistematica.class)
                .setParameter("caracterizacionId", caracterizacionId)
                .getResultList();
    }
}
