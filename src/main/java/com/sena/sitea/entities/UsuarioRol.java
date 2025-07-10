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
@Table(name = "usuario_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u"),
    @NamedQuery(name = "UsuarioRol.findByIdUsuarioRol", query = "SELECT u FROM UsuarioRol u WHERE u.idUsuarioRol = :idUsuarioRol")})
public class UsuarioRol implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_ROL")
    private Integer idUsuarioRol;
    @JoinColumn(name = "USUARIO_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuarioIdUsuario;
    
    @JoinColumn(name = "ROL_ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rolIdRol;

    public UsuarioRol() {
    }

    public UsuarioRol(Integer idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public Integer getIdUsuarioRol() {
        return idUsuarioRol;
    }

    public void setIdUsuarioRol(Integer idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public Usuarios getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuarios usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioRol != null ? idUsuarioRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.idUsuarioRol == null && other.idUsuarioRol != null) || (this.idUsuarioRol != null && !this.idUsuarioRol.equals(other.idUsuarioRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.UsuarioRol[ idUsuarioRol=" + idUsuarioRol + " ]";
    }

    public Rol getRolIdRol() {
        return rolIdRol;
    }

    public void setRolIdRol(Rol rolIdRol) {
        this.rolIdRol = rolIdRol;
    }
}
