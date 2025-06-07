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
@Table(name = "institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i"),
    @NamedQuery(name = "Institucion.findByIdInstitucion", query = "SELECT i FROM Institucion i WHERE i.idInstitucion = :idInstitucion"),
    @NamedQuery(name = "Institucion.findByNombreInstitucion", query = "SELECT i FROM Institucion i WHERE i.nombreInstitucion = :nombreInstitucion"),
    @NamedQuery(name = "Institucion.findByNitInstitucion", query = "SELECT i FROM Institucion i WHERE i.nitInstitucion = :nitInstitucion"),
    @NamedQuery(name = "Institucion.findByCorreoInstitucion", query = "SELECT i FROM Institucion i WHERE i.correoInstitucion = :correoInstitucion"),
    @NamedQuery(name = "Institucion.findByTelefonoInstitucion", query = "SELECT i FROM Institucion i WHERE i.telefonoInstitucion = :telefonoInstitucion"),
    @NamedQuery(name = "Institucion.findByDireccionInstitucion", query = "SELECT i FROM Institucion i WHERE i.direccionInstitucion = :direccionInstitucion")})
public class Institucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INSTITUCION")
    private Integer idInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOMBRE_INSTITUCION")
    private String nombreInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NIT_INSTITUCION")
    private String nitInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CORREO_INSTITUCION")
    private String correoInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TELEFONO_INSTITUCION")
    private String telefonoInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DIRECCION_INSTITUCION")
    private String direccionInstitucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionIdInstitucion", fetch = FetchType.LAZY)
    private List<Grado> gradoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionIdInstitucion", fetch = FetchType.LAZY)
    private List<RepresentanteLegal> representanteLegalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionIdInstitucion", fetch = FetchType.LAZY)
    private List<Matricula> matriculaList;

    public Institucion() {
    }

    public Institucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public Institucion(Integer idInstitucion, String nombreInstitucion, String nitInstitucion, String correoInstitucion, String telefonoInstitucion, String direccionInstitucion) {
        this.idInstitucion = idInstitucion;
        this.nombreInstitucion = nombreInstitucion;
        this.nitInstitucion = nitInstitucion;
        this.correoInstitucion = correoInstitucion;
        this.telefonoInstitucion = telefonoInstitucion;
        this.direccionInstitucion = direccionInstitucion;
    }

    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getNitInstitucion() {
        return nitInstitucion;
    }

    public void setNitInstitucion(String nitInstitucion) {
        this.nitInstitucion = nitInstitucion;
    }

    public String getCorreoInstitucion() {
        return correoInstitucion;
    }

    public void setCorreoInstitucion(String correoInstitucion) {
        this.correoInstitucion = correoInstitucion;
    }

    public String getTelefonoInstitucion() {
        return telefonoInstitucion;
    }

    public void setTelefonoInstitucion(String telefonoInstitucion) {
        this.telefonoInstitucion = telefonoInstitucion;
    }

    public String getDireccionInstitucion() {
        return direccionInstitucion;
    }

    public void setDireccionInstitucion(String direccionInstitucion) {
        this.direccionInstitucion = direccionInstitucion;
    }

    @XmlTransient
    public List<Grado> getGradoList() {
        return gradoList;
    }

    public void setGradoList(List<Grado> gradoList) {
        this.gradoList = gradoList;
    }

    @XmlTransient
    public List<RepresentanteLegal> getRepresentanteLegalList() {
        return representanteLegalList;
    }

    public void setRepresentanteLegalList(List<RepresentanteLegal> representanteLegalList) {
        this.representanteLegalList = representanteLegalList;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstitucion != null ? idInstitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.idInstitucion == null && other.idInstitucion != null) || (this.idInstitucion != null && !this.idInstitucion.equals(other.idInstitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Institucion[ idInstitucion=" + idInstitucion + " ]";
    }
    
}
