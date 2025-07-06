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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "rol_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolPermiso.findAll", query = "SELECT r FROM RolPermiso r"),
    @NamedQuery(name = "RolPermiso.findByIdRolPermiso", query = "SELECT r FROM RolPermiso r WHERE r.idRolPermiso = :idRolPermiso")})
public class RolPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ROL_PERMISO")
    private Integer idRolPermiso;
    @JoinColumn(name = "ROL_ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rol rolIdRol;
    @JoinColumn(name = "PERMISO_ID_PERMISO", referencedColumnName = "ID_PERMISO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Permisos permisoIdPermiso;

    public RolPermiso() {
    }

    public RolPermiso(Integer idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

    public Integer getIdRolPermiso() {
        return idRolPermiso;
    }

    public void setIdRolPermiso(Integer idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

    public Rol getRolIdRol() {
        return rolIdRol;
    }

    public void setRolIdRol(Rol rolIdRol) {
        this.rolIdRol = rolIdRol;
    }

    public Permisos getPermisoIdPermiso() {
        return permisoIdPermiso;
    }

    public void setPermisoIdPermiso(Permisos permisoIdPermiso) {
        this.permisoIdPermiso = permisoIdPermiso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolPermiso != null ? idRolPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolPermiso)) {
            return false;
        }
        RolPermiso other = (RolPermiso) object;
        if ((this.idRolPermiso == null && other.idRolPermiso != null) || (this.idRolPermiso != null && !this.idRolPermiso.equals(other.idRolPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.RolPermiso[ idRolPermiso=" + idRolPermiso + " ]";
    }
    
}
