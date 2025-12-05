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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "contexto_escolar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContextoEscolar.findAll", query = "SELECT c FROM ContextoEscolar c"),
    @NamedQuery(name = "ContextoEscolar.findByCaracterizacion", query = "SELECT c FROM ContextoEscolar c WHERE c.caracterizacion.idCaracterizacion = :id")
})
public class ContextoEscolar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contexto_escolar")
    private Integer idContextoEscolar;

    @JoinColumn(name = "id_caracterizacion", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacion;

    @Column(name = "INFRAESTRUCTURA", columnDefinition = "LONGTEXT")
    private String infraestructura;

    @Column(name = "ACCESIBILIDAD", columnDefinition = "LONGTEXT")
    private String accesibilidad;

    @Column(name = "RECURSOS", columnDefinition = "LONGTEXT")
    private String recursos;

    @Column(name = "AMBIENTE", columnDefinition = "LONGTEXT")
    private String ambiente;

    @Column(name = "OBSERVACIONES_DOCENTES", columnDefinition = "LONGTEXT")
    private String observacionesDocentes;

    @Column(name = "BARRERAS_APRENDIZAJE", columnDefinition = "LONGTEXT")
    private String barrerasAprendizaje;

    @Column(name = "RECOMENDACIONES_INSTITUCIONALES", columnDefinition = "LONGTEXT")
    private String recomendacionesInstitucionales;

    @Column(name = "OTRAS_NOTAS", columnDefinition = "LONGTEXT")
    private String otrasNotas;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "CREATED_BY")
    private Integer createdBy;

    @Column(name = "UPDATED_BY")
    private Integer updatedBy;

    public ContextoEscolar() {
    }

    public Integer getIdContextoEscolar() {
        return idContextoEscolar;
    }

    public void setIdContextoEscolar(Integer idContextoEscolar) {
        this.idContextoEscolar = idContextoEscolar;
    }

    public Caracterizacion getCaracterizacion() {
        return caracterizacion;
    }

    public void setCaracterizacion(Caracterizacion caracterizacion) {
        this.caracterizacion = caracterizacion;
    }

    public String getInfraestructura() {
        return infraestructura;
    }

    public void setInfraestructura(String infraestructura) {
        this.infraestructura = infraestructura;
    }

    public String getAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(String accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getObservacionesDocentes() {
        return observacionesDocentes;
    }

    public void setObservacionesDocentes(String observacionesDocentes) {
        this.observacionesDocentes = observacionesDocentes;
    }

    public String getBarrerasAprendizaje() {
        return barrerasAprendizaje;
    }

    public void setBarrerasAprendizaje(String barrerasAprendizaje) {
        this.barrerasAprendizaje = barrerasAprendizaje;
    }

    public String getRecomendacionesInstitucionales() {
        return recomendacionesInstitucionales;
    }

    public void setRecomendacionesInstitucionales(String recomendacionesInstitucionales) {
        this.recomendacionesInstitucionales = recomendacionesInstitucionales;
    }

    public String getOtrasNotas() {
        return otrasNotas;
    }

    public void setOtrasNotas(String otrasNotas) {
        this.otrasNotas = otrasNotas;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}
