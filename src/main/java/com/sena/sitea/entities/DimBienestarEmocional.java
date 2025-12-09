package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "dim_bienestar_emocional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DimBienestarEmocional.findAll", query = "SELECT d FROM DimBienestarEmocional d"),
    @NamedQuery(name = "DimBienestarEmocional.findByCaracterizacion", query = "SELECT d FROM DimBienestarEmocional d WHERE d.caracterizacionId = :caracterizacionId")
})
public class DimBienestarEmocional implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIM_EMOCIONAL")
    private Integer idDimEmocional;

    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacionId;

    @Column(name = "IDENTIFICA_EMOCIONES")
    private Integer identificaEmociones;
    @Column(name = "EXPRESA_EMOCIONES")
    private Integer expresaEmociones;
    @Column(name = "REGULA_IMPULSOS")
    private Integer regulaImpulsos;
    @Column(name = "AUTOESTIMA")
    private Integer autoestima;
    @Column(name = "MANEJO_ESTRES")
    private Integer manejoEstres;
    @Column(name = "EMPATIA")
    private Integer empatia;
    @Column(name = "ESCUCHA_ACTIVA")
    private Integer escuchaActiva;
    @Column(name = "RESUELVE_CONFLICTOS")
    private Integer resuelveConflictos;
    @Column(name = "TRABAJO_EQUIPO")
    private Integer trabajoEquipo;

    @Lob
    @Column(name = "SIGNOS_ALARMA")
    private String signosAlarma; // persisted as comma-separated values

    @Lob
    @Column(name = "OBSERVACIONES_PSICO")
    private String observacionesPsico;

    public DimBienestarEmocional() { }

    public DimBienestarEmocional(Integer idDimEmocional) { this.idDimEmocional = idDimEmocional; }

    public Integer getIdDimEmocional() { return idDimEmocional; }
    public void setIdDimEmocional(Integer idDimEmocional) { this.idDimEmocional = idDimEmocional; }

    public Caracterizacion getCaracterizacionId() { return caracterizacionId; }
    public void setCaracterizacionId(Caracterizacion caracterizacionId) { this.caracterizacionId = caracterizacionId; }

    public Integer getIdentificaEmociones() { return identificaEmociones; }
    public void setIdentificaEmociones(Integer identificaEmociones) { this.identificaEmociones = identificaEmociones; }

    public Integer getExpresaEmociones() { return expresaEmociones; }
    public void setExpresaEmociones(Integer expresaEmociones) { this.expresaEmociones = expresaEmociones; }

    public Integer getRegulaImpulsos() { return regulaImpulsos; }
    public void setRegulaImpulsos(Integer regulaImpulsos) { this.regulaImpulsos = regulaImpulsos; }

    public Integer getAutoestima() { return autoestima; }
    public void setAutoestima(Integer autoestima) { this.autoestima = autoestima; }

    public Integer getManejoEstres() { return manejoEstres; }
    public void setManejoEstres(Integer manejoEstres) { this.manejoEstres = manejoEstres; }

    public Integer getEmpatia() { return empatia; }
    public void setEmpatia(Integer empatia) { this.empatia = empatia; }

    public Integer getEscuchaActiva() { return escuchaActiva; }
    public void setEscuchaActiva(Integer escuchaActiva) { this.escuchaActiva = escuchaActiva; }

    public Integer getResuelveConflictos() { return resuelveConflictos; }
    public void setResuelveConflictos(Integer resuelveConflictos) { this.resuelveConflictos = resuelveConflictos; }

    public Integer getTrabajoEquipo() { return trabajoEquipo; }
    public void setTrabajoEquipo(Integer trabajoEquipo) { this.trabajoEquipo = trabajoEquipo; }

    public String getSignosAlarma() { return signosAlarma; }
    public void setSignosAlarma(String signosAlarma) { this.signosAlarma = signosAlarma; }

    // Property for JSF binding: treat signosAlarma as List<String> in the UI
    @javax.persistence.Transient
    public java.util.List<String> getSignosAlarmaList() {
        if (this.signosAlarma == null || this.signosAlarma.trim().isEmpty()) return new java.util.ArrayList<>();
        String[] parts = this.signosAlarma.split(",");
        java.util.List<String> out = new java.util.ArrayList<>();
        for (String p : parts) {
            String t = p.trim(); if (!t.isEmpty()) out.add(t);
        }
        return out;
    }

    @javax.persistence.Transient
    public void setSignosAlarmaList(java.util.List<String> list) {
        if (list == null || list.isEmpty()) {
            this.signosAlarma = null;
        } else {
            this.signosAlarma = String.join(", ", list);
        }
    }

    public String getObservacionesPsico() { return observacionesPsico; }
    public void setObservacionesPsico(String observacionesPsico) { this.observacionesPsico = observacionesPsico; }
}
