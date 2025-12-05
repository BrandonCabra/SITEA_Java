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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad para gestionar las 8 dimensiones de valoración según MEN
 * RF-009: Valoración sistemática por las 8 dimensiones
 * 
 * @author SITEA
 */
@Entity
@Table(name = "dimension_valoracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DimensionValoracion.findAll", query = "SELECT d FROM DimensionValoracion d"),
    @NamedQuery(name = "DimensionValoracion.findByCaracterizacion", query = "SELECT d FROM DimensionValoracion d WHERE d.caracterizacionId = :caracterizacionId"),
    @NamedQuery(name = "DimensionValoracion.findByEstado", query = "SELECT d FROM DimensionValoracion d WHERE d.estado = :estado")
})
public class DimensionValoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIMENSION")
    private Integer idDimension;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_DIMENSION")
    private String nombreDimension;
    
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Lob
    @Size(max = 65535)
    @Column(name = "FORTALEZAS")
    private String fortalezas;
    
    @Lob
    @Size(max = 65535)
    @Column(name = "AREAS_APOYO")
    private String areasApoyo;
    
    @Column(name = "PUNTUACION")
    private Integer puntuacion;
    
    @Size(max = 20)
    @Column(name = "ESTADO")
    private String estado; // PENDIENTE, EN_PROCESO, COMPLETADA
    
    @Column(name = "FECHA_VALORACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaValoracion;
    
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacionId;

    public DimensionValoracion() {
    }

    public DimensionValoracion(Integer idDimension) {
        this.idDimension = idDimension;
    }

    // Getters y Setters
    public Integer getIdDimension() {
        return idDimension;
    }

    public void setIdDimension(Integer idDimension) {
        this.idDimension = idDimension;
    }

    public String getNombreDimension() {
        return nombreDimension;
    }

    public void setNombreDimension(String nombreDimension) {
        this.nombreDimension = nombreDimension;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFortalezas() {
        return fortalezas;
    }

    public void setFortalezas(String fortalezas) {
        this.fortalezas = fortalezas;
    }

    public String getAreasApoyo() {
        return areasApoyo;
    }

    public void setAreasApoyo(String areasApoyo) {
        this.areasApoyo = areasApoyo;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaValoracion() {
        return fechaValoracion;
    }

    public void setFechaValoracion(Date fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
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

    public Caracterizacion getCaracterizacionId() {
        return caracterizacionId;
    }

    public void setCaracterizacionId(Caracterizacion caracterizacionId) {
        this.caracterizacionId = caracterizacionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDimension != null ? idDimension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DimensionValoracion)) {
            return false;
        }
        DimensionValoracion other = (DimensionValoracion) object;
        if ((this.idDimension == null && other.idDimension != null) || 
            (this.idDimension != null && !this.idDimension.equals(other.idDimension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DimensionValoracion[ idDimension=" + idDimension + " ]";
    }
}
