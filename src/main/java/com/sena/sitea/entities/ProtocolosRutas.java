/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "protocolos_rutas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProtocolosRutas.findAll", query = "SELECT p FROM ProtocolosRutas p"),
    @NamedQuery(name = "ProtocolosRutas.findByIdProtocolosRutas", query = "SELECT p FROM ProtocolosRutas p WHERE p.idProtocolosRutas = :idProtocolosRutas"),
    @NamedQuery(name = "ProtocolosRutas.findByNombreProtocolosRutas", query = "SELECT p FROM ProtocolosRutas p WHERE p.nombreProtocolosRutas = :nombreProtocolosRutas"),
    @NamedQuery(name = "ProtocolosRutas.findByTipoProtocolosRutas", query = "SELECT p FROM ProtocolosRutas p WHERE p.tipoProtocolosRutas = :tipoProtocolosRutas"),
    @NamedQuery(name = "ProtocolosRutas.findByResponsableProtocolosRutas", query = "SELECT p FROM ProtocolosRutas p WHERE p.responsableProtocolosRutas = :responsableProtocolosRutas"),
    @NamedQuery(name = "ProtocolosRutas.findByEstadoProtocolosRutas", query = "SELECT p FROM ProtocolosRutas p WHERE p.estadoProtocolosRutas = :estadoProtocolosRutas")})
public class ProtocolosRutas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROTOCOLOS_RUTAS")
    private Integer idProtocolosRutas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE_PROTOCOLOS_RUTAS")
    private String nombreProtocolosRutas;
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRIPCION_PROTOCOLOS_RUTAS")
    private String descripcionProtocolosRutas;
    @Size(max = 11)
    @Column(name = "TIPO_PROTOCOLOS_RUTAS")
    private String tipoProtocolosRutas;
    @Lob
    @Size(max = 65535)
    @Column(name = "PASOS_PROTOCOLOS_RUTAS")
    private String pasosProtocolosRutas;
    @Size(max = 100)
    @Column(name = "RESPONSABLE_PROTOCOLOS_RUTAS")
    private String responsableProtocolosRutas;
    @Size(max = 10)
    @Column(name = "ESTADO_PROTOCOLOS_RUTAS")
    private String estadoProtocolosRutas;
    @JoinColumn(name = "NOVEDADES_REPORTES_ID_NOVEDADES_REPORTES", referencedColumnName = "ID_NOVEDADES_REPORTES")
    @ManyToOne(fetch = FetchType.LAZY)
    private NovedadesReportes novedadesReportesIdNovedadesReportes;

    public ProtocolosRutas() {
    }

    public ProtocolosRutas(Integer idProtocolosRutas) {
        this.idProtocolosRutas = idProtocolosRutas;
    }

    public ProtocolosRutas(Integer idProtocolosRutas, String nombreProtocolosRutas) {
        this.idProtocolosRutas = idProtocolosRutas;
        this.nombreProtocolosRutas = nombreProtocolosRutas;
    }

    public Integer getIdProtocolosRutas() {
        return idProtocolosRutas;
    }

    public void setIdProtocolosRutas(Integer idProtocolosRutas) {
        this.idProtocolosRutas = idProtocolosRutas;
    }

    public String getNombreProtocolosRutas() {
        return nombreProtocolosRutas;
    }

    public void setNombreProtocolosRutas(String nombreProtocolosRutas) {
        this.nombreProtocolosRutas = nombreProtocolosRutas;
    }

    public String getDescripcionProtocolosRutas() {
        return descripcionProtocolosRutas;
    }

    public void setDescripcionProtocolosRutas(String descripcionProtocolosRutas) {
        this.descripcionProtocolosRutas = descripcionProtocolosRutas;
    }

    public String getTipoProtocolosRutas() {
        return tipoProtocolosRutas;
    }

    public void setTipoProtocolosRutas(String tipoProtocolosRutas) {
        this.tipoProtocolosRutas = tipoProtocolosRutas;
    }

    public String getPasosProtocolosRutas() {
        return pasosProtocolosRutas;
    }

    public void setPasosProtocolosRutas(String pasosProtocolosRutas) {
        this.pasosProtocolosRutas = pasosProtocolosRutas;
    }

    public String getResponsableProtocolosRutas() {
        return responsableProtocolosRutas;
    }

    public void setResponsableProtocolosRutas(String responsableProtocolosRutas) {
        this.responsableProtocolosRutas = responsableProtocolosRutas;
    }

    public String getEstadoProtocolosRutas() {
        return estadoProtocolosRutas;
    }

    public void setEstadoProtocolosRutas(String estadoProtocolosRutas) {
        this.estadoProtocolosRutas = estadoProtocolosRutas;
    }

    public NovedadesReportes getNovedadesReportesIdNovedadesReportes() {
        return novedadesReportesIdNovedadesReportes;
    }

    public void setNovedadesReportesIdNovedadesReportes(NovedadesReportes novedadesReportesIdNovedadesReportes) {
        this.novedadesReportesIdNovedadesReportes = novedadesReportesIdNovedadesReportes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProtocolosRutas != null ? idProtocolosRutas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProtocolosRutas)) {
            return false;
        }
        ProtocolosRutas other = (ProtocolosRutas) object;
        if ((this.idProtocolosRutas == null && other.idProtocolosRutas != null) || (this.idProtocolosRutas != null && !this.idProtocolosRutas.equals(other.idProtocolosRutas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.ProtocolosRutas[ idProtocolosRutas=" + idProtocolosRutas + " ]";
    }
    
}
