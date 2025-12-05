package com.sena.sitea.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad para mantener el contador de expedientes por año.
 */
@Entity
@Table(name = "expediente_counters")
public class ExpedienteCounter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "year")
    private Integer year;

    @Column(name = "last_counter")
    private Integer lastCounter;

    public ExpedienteCounter() {
    }

    public ExpedienteCounter(Integer year, Integer lastCounter) {
        this.year = year;
        this.lastCounter = lastCounter;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getLastCounter() {
        return lastCounter;
    }

    public void setLastCounter(Integer lastCounter) {
        this.lastCounter = lastCounter;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (year != null ? year.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ExpedienteCounter)) {
            return false;
        }
        ExpedienteCounter other = (ExpedienteCounter) object;
        if ((this.year == null && other.year != null) || (this.year != null && !this.year.equals(other.year))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.ExpedienteCounter[ year=" + year + " ]";
    }

}
