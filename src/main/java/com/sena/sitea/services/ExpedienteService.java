package com.sena.sitea.services;

import com.sena.sitea.entities.ExpedienteCounter;
import java.time.LocalDate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 * Servicio para generación transaccional de códigos de expediente.
 */
@Stateless
public class ExpedienteService {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    /**
     * Genera un código de expediente. Si temporary == true genera TEMP-TEA-YYYY-####
     * si no, EXP-TEA-YYYY-####.
     */
    public String generateExpediente(boolean temporary) {
        int year = LocalDate.now().getYear();
        int seq = 1;

        ExpedienteCounter counter = em.find(ExpedienteCounter.class, year, LockModeType.PESSIMISTIC_WRITE);
        if (counter == null) {
            counter = new ExpedienteCounter(year, 1);
            em.persist(counter);
            seq = 1;
        } else {
            seq = (counter.getLastCounter() == null ? 1 : counter.getLastCounter() + 1);
            counter.setLastCounter(seq);
            em.merge(counter);
        }

        String prefix = temporary ? "TEMP-TEA" : "EXP-TEA";
        return String.format("%s-%d-%04d", prefix, year, seq);
    }

}
