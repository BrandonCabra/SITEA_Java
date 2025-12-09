package com.sena.sitea.services;

import com.sena.sitea.entities.DimParticipacionSocial;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DimParticipacionSocialFacade extends AbstractFacade<DimParticipacionSocial> implements DimParticipacionSocialFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DimParticipacionSocialFacade() {
        super(DimParticipacionSocial.class);
    }

    @Override
    public List<DimParticipacionSocial> findByCaracterizacion(Integer caracterizacionId) {
        return em.createNamedQuery("DimParticipacionSocial.findByCaracterizacion", DimParticipacionSocial.class)
                .setParameter("caracterizacionId", caracterizacionId)
                .getResultList();
    }
}
