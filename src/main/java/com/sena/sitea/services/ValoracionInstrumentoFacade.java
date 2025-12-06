package com.sena.sitea.services;

import com.sena.sitea.entities.ValoracionInstrumento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ValoracionInstrumentoFacade extends AbstractFacade<ValoracionInstrumento> implements ValoracionInstrumentoFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() { return em; }

    public ValoracionInstrumentoFacade() { super(ValoracionInstrumento.class); }
}
