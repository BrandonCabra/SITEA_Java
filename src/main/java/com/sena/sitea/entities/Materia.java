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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
    @NamedQuery(name = "Materia.findByIdMateria", query = "SELECT m FROM Materia m WHERE m.idMateria = :idMateria"),
    @NamedQuery(name = "Materia.findByCodigoMateria", query = "SELECT m FROM Materia m WHERE m.codigoMateria = :codigoMateria"),
    @NamedQuery(name = "Materia.findByNombreMateria", query = "SELECT m FROM Materia m WHERE m.nombreMateria = :nombreMateria"),
    @NamedQuery(name = "Materia.findByDescripcionMateria", query = "SELECT m FROM Materia m WHERE m.descripcionMateria = :descripcionMateria"),
    @NamedQuery(name = "Materia.findByPlanMateria", query = "SELECT m FROM Materia m WHERE m.planMateria = :planMateria")})
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MATERIA")
    private Integer idMateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CODIGO_MATERIA")
    private String codigoMateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE_MATERIA")
    private String nombreMateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "DESCRIPCION_MATERIA")
    private String descripcionMateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PLAN_MATERIA")
    private String planMateria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaIdMateria", fetch = FetchType.LAZY)
    private List<GradoMateria> gradoMateriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaIdMateria", fetch = FetchType.LAZY)
    private List<UsuarioprofHasMateria> usuarioprofHasMateriaList;
    @JoinColumn(name = "AREA_ID_AREA", referencedColumnName = "ID_AREA")
    @ManyToOne(fetch = FetchType.LAZY)
    private Area areaIdArea;

    public Materia() {
    }

    public Materia(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Materia(Integer idMateria, String codigoMateria, String nombreMateria, String descripcionMateria, String planMateria) {
        this.idMateria = idMateria;
        this.codigoMateria = codigoMateria;
        this.nombreMateria = nombreMateria;
        this.descripcionMateria = descripcionMateria;
        this.planMateria = planMateria;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getDescripcionMateria() {
        return descripcionMateria;
    }

    public void setDescripcionMateria(String descripcionMateria) {
        this.descripcionMateria = descripcionMateria;
    }

    public String getPlanMateria() {
        return planMateria;
    }

    public void setPlanMateria(String planMateria) {
        this.planMateria = planMateria;
    }

    @XmlTransient
    public List<GradoMateria> getGradoMateriaList() {
        return gradoMateriaList;
    }

    public void setGradoMateriaList(List<GradoMateria> gradoMateriaList) {
        this.gradoMateriaList = gradoMateriaList;
    }

    @XmlTransient
    public List<UsuarioprofHasMateria> getUsuarioprofHasMateriaList() {
        return usuarioprofHasMateriaList;
    }

    public void setUsuarioprofHasMateriaList(List<UsuarioprofHasMateria> usuarioprofHasMateriaList) {
        this.usuarioprofHasMateriaList = usuarioprofHasMateriaList;
    }

    public Area getAreaIdArea() {
        return areaIdArea;
    }

    public void setAreaIdArea(Area areaIdArea) {
        this.areaIdArea = areaIdArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateria != null ? idMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Materia[ idMateria=" + idMateria + " ]";
    }
    
}
