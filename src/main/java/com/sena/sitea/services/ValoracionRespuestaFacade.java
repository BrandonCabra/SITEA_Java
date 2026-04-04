package com.sena.sitea.services;

import com.sena.sitea.entities.ValoracionRespuesta;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ValoracionRespuestaFacade extends AbstractFacade<ValoracionRespuesta> implements ValoracionRespuestaFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() { return em; }

    public ValoracionRespuestaFacade() { super(ValoracionRespuesta.class); }
}
