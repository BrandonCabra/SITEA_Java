/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "entidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entidades.findAll", query = "SELECT e FROM Entidades e"),
    @NamedQuery(name = "Entidades.findByIdEntidad", query = "SELECT e FROM Entidades e WHERE e.idEntidad = :idEntidad"),
    @NamedQuery(name = "Entidades.findByNombreEntidad", query = "SELECT e FROM Entidades e WHERE e.nombreEntidad = :nombreEntidad"),
    @NamedQuery(name = "Entidades.findByModulo", query = "SELECT e FROM Entidades e WHERE e.modulo = :modulo")})
public class Entidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ENTIDAD")
    private Integer idEntidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_ENTIDAD")
    private String nombreEntidad;
    @Size(max = 100)
    @Column(name = "MODULO")
    private String modulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidadIdEntidad", fetch = FetchType.LAZY)
    private List<Permisos> permisosList;

    public Entidades() {
    }

    public Entidades(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Entidades(Integer idEntidad, String nombreEntidad) {
        this.idEntidad = idEntidad;
        this.nombreEntidad = nombreEntidad;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
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
        hash += (idEntidad != null ? idEntidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidades)) {
            return false;
        }
        Entidades other = (Entidades) object;
        if ((this.idEntidad == null && other.idEntidad != null) || (this.idEntidad != null && !this.idEntidad.equals(other.idEntidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Entidades[ idEntidad=" + idEntidad + " ]";
    }
    
}
