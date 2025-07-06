/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "novedades_reportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NovedadesReportes.findAll", query = "SELECT n FROM NovedadesReportes n"),
    @NamedQuery(name = "NovedadesReportes.findByIdNovedadesReportes", query = "SELECT n FROM NovedadesReportes n WHERE n.idNovedadesReportes = :idNovedadesReportes"),
    @NamedQuery(name = "NovedadesReportes.findByFechaNovedadesReportes", query = "SELECT n FROM NovedadesReportes n WHERE n.fechaNovedadesReportes = :fechaNovedadesReportes"),
    @NamedQuery(name = "NovedadesReportes.findByNumeroDocumentoEstudiante", query = "SELECT n FROM NovedadesReportes n WHERE n.numeroDocumentoEstudiante = :numeroDocumentoEstudiante"),
    @NamedQuery(name = "NovedadesReportes.findByAccionesNovedadesReportes", query = "SELECT n FROM NovedadesReportes n WHERE n.accionesNovedadesReportes = :accionesNovedadesReportes"),
    @NamedQuery(name = "NovedadesReportes.findByTipoNovedadesReportes", query = "SELECT n FROM NovedadesReportes n WHERE n.tipoNovedadesReportes = :tipoNovedadesReportes"),
    @NamedQuery(name = "NovedadesReportes.findByDescripcionNovedadesReportes", query = "SELECT n FROM NovedadesReportes n WHERE n.descripcionNovedadesReportes = :descripcionNovedadesReportes"),
    @NamedQuery(name = "NovedadesReportes.findByEstadoNovedadesReportes", query = "SELECT n FROM NovedadesReportes n WHERE n.estadoNovedadesReportes = :estadoNovedadesReportes"),
    @NamedQuery(name = "NovedadesReportes.findByPrioridad", query = "SELECT n FROM NovedadesReportes n WHERE n.prioridad = :prioridad")})
public class NovedadesReportes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_NOVEDADES_REPORTES")
    private Integer idNovedadesReportes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NOVEDADES_REPORTES")
    @Temporal(TemporalType.DATE)
    private Date fechaNovedadesReportes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NUMERO_DOCUMENTO_ESTUDIANTE")
    private String numeroDocumentoEstudiante;
    @Size(max = 100)
    @Column(name = "ACCIONES_NOVEDADES_REPORTES")
    private String accionesNovedadesReportes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "TIPO_NOVEDADES_REPORTES")
    private String tipoNovedadesReportes;
    @Size(max = 200)
    @Column(name = "DESCRIPCION_NOVEDADES_REPORTES")
    private String descripcionNovedadesReportes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "ESTADO_NOVEDADES_REPORTES")
    private String estadoNovedadesReportes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "PRIORIDAD")
    private String prioridad;
    @OneToMany(mappedBy = "novedadesReportesIdNovedadesReportes", fetch = FetchType.LAZY)
    private List<BoletinAcademico> boletinAcademicoList;
    @JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuariosIdUsuario;
    @OneToMany(mappedBy = "novedadesReportesIdNovedadesReportes", fetch = FetchType.LAZY)
    private List<ProtocolosRutas> protocolosRutasList;

    public NovedadesReportes() {
    }

    public NovedadesReportes(Integer idNovedadesReportes) {
        this.idNovedadesReportes = idNovedadesReportes;
    }

    public NovedadesReportes(Integer idNovedadesReportes, Date fechaNovedadesReportes, String numeroDocumentoEstudiante, String tipoNovedadesReportes, String estadoNovedadesReportes, String prioridad) {
        this.idNovedadesReportes = idNovedadesReportes;
        this.fechaNovedadesReportes = fechaNovedadesReportes;
        this.numeroDocumentoEstudiante = numeroDocumentoEstudiante;
        this.tipoNovedadesReportes = tipoNovedadesReportes;
        this.estadoNovedadesReportes = estadoNovedadesReportes;
        this.prioridad = prioridad;
    }

    public Integer getIdNovedadesReportes() {
        return idNovedadesReportes;
    }

    public void setIdNovedadesReportes(Integer idNovedadesReportes) {
        this.idNovedadesReportes = idNovedadesReportes;
    }

    public Date getFechaNovedadesReportes() {
        return fechaNovedadesReportes;
    }

    public void setFechaNovedadesReportes(Date fechaNovedadesReportes) {
        this.fechaNovedadesReportes = fechaNovedadesReportes;
    }

    public String getNumeroDocumentoEstudiante() {
        return numeroDocumentoEstudiante;
    }

    public void setNumeroDocumentoEstudiante(String numeroDocumentoEstudiante) {
        this.numeroDocumentoEstudiante = numeroDocumentoEstudiante;
    }

    public String getAccionesNovedadesReportes() {
        return accionesNovedadesReportes;
    }

    public void setAccionesNovedadesReportes(String accionesNovedadesReportes) {
        this.accionesNovedadesReportes = accionesNovedadesReportes;
    }

    public String getTipoNovedadesReportes() {
        return tipoNovedadesReportes;
    }

    public void setTipoNovedadesReportes(String tipoNovedadesReportes) {
        this.tipoNovedadesReportes = tipoNovedadesReportes;
    }

    public String getDescripcionNovedadesReportes() {
        return descripcionNovedadesReportes;
    }

    public void setDescripcionNovedadesReportes(String descripcionNovedadesReportes) {
        this.descripcionNovedadesReportes = descripcionNovedadesReportes;
    }

    public String getEstadoNovedadesReportes() {
        return estadoNovedadesReportes;
    }

    public void setEstadoNovedadesReportes(String estadoNovedadesReportes) {
        this.estadoNovedadesReportes = estadoNovedadesReportes;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    @XmlTransient
    public List<BoletinAcademico> getBoletinAcademicoList() {
        return boletinAcademicoList;
    }

    public void setBoletinAcademicoList(List<BoletinAcademico> boletinAcademicoList) {
        this.boletinAcademicoList = boletinAcademicoList;
    }

    public Usuarios getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(Usuarios usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    @XmlTransient
    public List<ProtocolosRutas> getProtocolosRutasList() {
        return protocolosRutasList;
    }

    public void setProtocolosRutasList(List<ProtocolosRutas> protocolosRutasList) {
        this.protocolosRutasList = protocolosRutasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNovedadesReportes != null ? idNovedadesReportes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NovedadesReportes)) {
            return false;
        }
        NovedadesReportes other = (NovedadesReportes) object;
        if ((this.idNovedadesReportes == null && other.idNovedadesReportes != null) || (this.idNovedadesReportes != null && !this.idNovedadesReportes.equals(other.idNovedadesReportes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.NovedadesReportes[ idNovedadesReportes=" + idNovedadesReportes + " ]";
    }
    
}
