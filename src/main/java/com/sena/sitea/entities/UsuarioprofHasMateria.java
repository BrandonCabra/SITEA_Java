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
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "usuarioprof_has_materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioprofHasMateria.findAll", query = "SELECT u FROM UsuarioprofHasMateria u"),
    @NamedQuery(name = "UsuarioprofHasMateria.findByIdUsuarioprofMateria", query = "SELECT u FROM UsuarioprofHasMateria u WHERE u.idUsuarioprofMateria = :idUsuarioprofMateria")})
public class UsuarioprofHasMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIOPROF_MATERIA")
    private Integer idUsuarioprofMateria;
    @JoinColumn(name = "MATERIA_ID_MATERIA", referencedColumnName = "ID_MATERIA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Materia materiaIdMateria;
    @JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuariosIdUsuario;
    @JoinColumn(name = "USUARIOS_ROL_ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol usuariosRolIdRol;

    public UsuarioprofHasMateria() {
    }

    public UsuarioprofHasMateria(Integer idUsuarioprofMateria) {
        this.idUsuarioprofMateria = idUsuarioprofMateria;
    }

    public Integer getIdUsuarioprofMateria() {
        return idUsuarioprofMateria;
    }

    public void setIdUsuarioprofMateria(Integer idUsuarioprofMateria) {
        this.idUsuarioprofMateria = idUsuarioprofMateria;
    }

    public Materia getMateriaIdMateria() {
        return materiaIdMateria;
    }

    public void setMateriaIdMateria(Materia materiaIdMateria) {
        this.materiaIdMateria = materiaIdMateria;
    }

    public Usuarios getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(Usuarios usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    public Rol getUsuariosRolIdRol() {
        return usuariosRolIdRol;
    }

    public void setUsuariosRolIdRol(Rol usuariosRolIdRol) {
        this.usuariosRolIdRol = usuariosRolIdRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioprofMateria != null ? idUsuarioprofMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioprofHasMateria)) {
            return false;
        }
        UsuarioprofHasMateria other = (UsuarioprofHasMateria) object;
        if ((this.idUsuarioprofMateria == null && other.idUsuarioprofMateria != null) || (this.idUsuarioprofMateria != null && !this.idUsuarioprofMateria.equals(other.idUsuarioprofMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.UsuarioprofHasMateria[ idUsuarioprofMateria=" + idUsuarioprofMateria + " ]";
    }
    
}
