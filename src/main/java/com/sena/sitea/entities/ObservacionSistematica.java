package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Entidad para gestionar observaciones sistemáticas del estudiante
 * RF-015: Registro de observaciones estructuradas diferenciadas por entorno
 * HU-004: Gestión de observación sistemática
 * 
 * @author SITEA
 */
@Entity
@Table(name = "observacion_sistematica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObservacionSistematica.findAll", query = "SELECT o FROM ObservacionSistematica o"),
    @NamedQuery(name = "ObservacionSistematica.findByCaracterizacion", query = "SELECT o FROM ObservacionSistematica o WHERE o.caracterizacionId = :caracterizacionId ORDER BY o.fechaObservacion DESC"),
    @NamedQuery(name = "ObservacionSistematica.findByEntorno", query = "SELECT o FROM ObservacionSistematica o WHERE o.entorno = :entorno")
})
public class ObservacionSistematica implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OBSERVACION")
    private Integer idObservacion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_OBSERVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaObservacion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ENTORNO")
    private String entorno; // AULA, RECREO, HOGAR, EXTRACURRICULAR
    
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Lob
    @Size(max = 65535)
    @Column(name = "CONTEXTO")
    private String contexto;
    
    @Size(max = 300)
    @Column(name = "EVIDENCIAS")
    private String evidencias; // Ruta a archivos adjuntos
    
    @Size(max = 100)
    @Column(name = "OBSERVADOR")
    private String observador;
    
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacionId;

    public ObservacionSistematica() {
    }

    public ObservacionSistematica(Integer idObservacion) {
        this.idObservacion = idObservacion;
    }

    // Getters y Setters
    public Integer getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(Integer idObservacion) {
        this.idObservacion = idObservacion;
    }

    public Date getFechaObservacion() {
        return fechaObservacion;
    }

    public void setFechaObservacion(Date fechaObservacion) {
        this.fechaObservacion = fechaObservacion;
    }

    public String getEntorno() {
        return entorno;
    }

    public void setEntorno(String entorno) {
        this.entorno = entorno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public String getEvidencias() {
        return evidencias;
    }

    public void setEvidencias(String evidencias) {
        this.evidencias = evidencias;
    }

    public String getObservador() {
        return observador;
    }

    public void setObservador(String observador) {
        this.observador = observador;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Caracterizacion getCaracterizacionId() {
        return caracterizacionId;
    }

    public void setCaracterizacionId(Caracterizacion caracterizacionId) {
        this.caracterizacionId = caracterizacionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObservacion != null ? idObservacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ObservacionSistematica)) {
            return false;
        }
        ObservacionSistematica other = (ObservacionSistematica) object;
        if ((this.idObservacion == null && other.idObservacion != null) || 
            (this.idObservacion != null && !this.idObservacion.equals(other.idObservacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ObservacionSistematica[ idObservacion=" + idObservacion + " ]";
    }
}
