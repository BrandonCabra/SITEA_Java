/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "acciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acciones.findAll", query = "SELECT a FROM Acciones a"),
    @NamedQuery(name = "Acciones.findByIdAcciones", query = "SELECT a FROM Acciones a WHERE a.idAcciones = :idAcciones"),
    @NamedQuery(name = "Acciones.findByNombreAccion", query = "SELECT a FROM Acciones a WHERE a.nombreAccion = :nombreAccion")})
public class Acciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACCIONES")
    private Integer idAcciones;

    
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE_ACCION")
    private String nombreAccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accionIdAccion", fetch = FetchType.LAZY)
    private List<Permisos> permisosList;

    public Acciones() {
    }

    public Acciones(Integer idAcciones) {
        this.idAcciones = idAcciones;
    }

    public Acciones(Integer idAcciones, String nombreAccion) {
        this.idAcciones = idAcciones;
        this.nombreAccion = nombreAccion;
    }

    public Integer getIdAcciones() {
        return idAcciones;
    }

    public void setIdAcciones(Integer idAcciones) {
        this.idAcciones = idAcciones;
    }

    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    @XmlTransient
    public List<Permisos> getPermisosList() {
        return permisosList;
    }

    public void setPermisosList(List<Permisos> permisosList) {
        this.permisosList = permisosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcciones != null ? idAcciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acciones)) {
            return false;
        }
        Acciones other = (Acciones) object;
        if ((this.idAcciones == null && other.idAcciones != null) || (this.idAcciones != null && !this.idAcciones.equals(other.idAcciones))) {
            return false;
        }
        return true;
    }
   
    
    @Override
    public String toString() {
        return "com.sena.sitea.entities.Acciones[ idAccion=" + idAcciones + " ]";
    }
    
    
}
