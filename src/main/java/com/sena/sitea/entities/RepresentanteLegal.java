/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "representante_legal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepresentanteLegal.findAll", query = "SELECT r FROM RepresentanteLegal r"),
    @NamedQuery(name = "RepresentanteLegal.findByIdRepresentanteLegal", query = "SELECT r FROM RepresentanteLegal r WHERE r.idRepresentanteLegal = :idRepresentanteLegal"),
    @NamedQuery(name = "RepresentanteLegal.findByNombreRepresentanteLegal", query = "SELECT r FROM RepresentanteLegal r WHERE r.nombreRepresentanteLegal = :nombreRepresentanteLegal"),
    @NamedQuery(name = "RepresentanteLegal.findByDocumentoRepresentanteLegal", query = "SELECT r FROM RepresentanteLegal r WHERE r.documentoRepresentanteLegal = :documentoRepresentanteLegal"),
    @NamedQuery(name = "RepresentanteLegal.findByTelefonoRepresentanteLegal", query = "SELECT r FROM RepresentanteLegal r WHERE r.telefonoRepresentanteLegal = :telefonoRepresentanteLegal"),
    @NamedQuery(name = "RepresentanteLegal.findByCorreoRepresentanteLegal", query = "SELECT r FROM RepresentanteLegal r WHERE r.correoRepresentanteLegal = :correoRepresentanteLegal")})
public class RepresentanteLegal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REPRESENTANTE_LEGAL")
    private Integer idRepresentanteLegal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE_REPRESENTANTE_LEGAL")
    private String nombreRepresentanteLegal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DOCUMENTO_REPRESENTANTE_LEGAL")
    private String documentoRepresentanteLegal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TELEFONO_REPRESENTANTE_LEGAL")
    private String telefonoRepresentanteLegal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CORREO_REPRESENTANTE_LEGAL")
    private String correoRepresentanteLegal;
    @JoinColumn(name = "INSTITUCION_ID_INSTITUCION", referencedColumnName = "ID_INSTITUCION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Institucion institucionIdInstitucion;

    public RepresentanteLegal() {
    }

    public RepresentanteLegal(Integer idRepresentanteLegal) {
        this.idRepresentanteLegal = idRepresentanteLegal;
    }

    public RepresentanteLegal(Integer idRepresentanteLegal, String nombreRepresentanteLegal, String documentoRepresentanteLegal, String telefonoRepresentanteLegal, String correoRepresentanteLegal) {
        this.idRepresentanteLegal = idRepresentanteLegal;
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
        this.documentoRepresentanteLegal = documentoRepresentanteLegal;
        this.telefonoRepresentanteLegal = telefonoRepresentanteLegal;
        this.correoRepresentanteLegal = correoRepresentanteLegal;
    }

    public Integer getIdRepresentanteLegal() {
        return idRepresentanteLegal;
    }

    public void setIdRepresentanteLegal(Integer idRepresentanteLegal) {
        this.idRepresentanteLegal = idRepresentanteLegal;
    }

    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    public String getDocumentoRepresentanteLegal() {
        return documentoRepresentanteLegal;
    }

    public void setDocumentoRepresentanteLegal(String documentoRepresentanteLegal) {
        this.documentoRepresentanteLegal = documentoRepresentanteLegal;
    }

    public String getTelefonoRepresentanteLegal() {
        return telefonoRepresentanteLegal;
    }

    public void setTelefonoRepresentanteLegal(String telefonoRepresentanteLegal) {
        this.telefonoRepresentanteLegal = telefonoRepresentanteLegal;
    }

    public String getCorreoRepresentanteLegal() {
        return correoRepresentanteLegal;
    }

    public void setCorreoRepresentanteLegal(String correoRepresentanteLegal) {
        this.correoRepresentanteLegal = correoRepresentanteLegal;
    }

    public Institucion getInstitucionIdInstitucion() {
        return institucionIdInstitucion;
    }

    public void setInstitucionIdInstitucion(Institucion institucionIdInstitucion) {
        this.institucionIdInstitucion = institucionIdInstitucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRepresentanteLegal != null ? idRepresentanteLegal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepresentanteLegal)) {
            return false;
        }
        RepresentanteLegal other = (RepresentanteLegal) object;
        if ((this.idRepresentanteLegal == null && other.idRepresentanteLegal != null) || (this.idRepresentanteLegal != null && !this.idRepresentanteLegal.equals(other.idRepresentanteLegal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.RepresentanteLegal[ idRepresentanteLegal=" + idRepresentanteLegal + " ]";
    }
    
}
