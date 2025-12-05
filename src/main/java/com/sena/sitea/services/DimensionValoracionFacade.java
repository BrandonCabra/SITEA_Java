package com.sena.sitea.services;

import com.sena.sitea.entities.DimensionValoracion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SITEA
 */
@Stateless
public class DimensionValoracionFacade extends AbstractFacade<DimensionValoracion> implements DimensionValoracionFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DimensionValoracionFacade() {
        super(DimensionValoracion.class);
    }
    
    @Override
    public List<DimensionValoracion> findByCaracterizacion(Integer caracterizacionId) {
        return em.createNamedQuery("DimensionValoracion.findByCaracterizacion", DimensionValoracion.class)
                .setParameter("caracterizacionId", caracterizacionId)
                .getResultList();
    }
}
