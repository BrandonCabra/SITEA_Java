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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "dba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dba.findAll", query = "SELECT d FROM Dba d"),
    @NamedQuery(name = "Dba.findByIdDba", query = "SELECT d FROM Dba d WHERE d.idDba = :idDba"),
    @NamedQuery(name = "Dba.findByObjetivoEnunciado", query = "SELECT d FROM Dba d WHERE d.objetivoEnunciado = :objetivoEnunciado"),
    @NamedQuery(name = "Dba.findByEvidencia", query = "SELECT d FROM Dba d WHERE d.evidencia = :evidencia"),
    @NamedQuery(name = "Dba.findByEjemplo", query = "SELECT d FROM Dba d WHERE d.ejemplo = :ejemplo")})
public class Dba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DBA")
    private Integer idDba;
    @Size(max = 400)
    @Column(name = "OBJETIVO_ENUNCIADO")
    private String objetivoEnunciado;
    @Size(max = 400)
    @Column(name = "EVIDENCIA")
    private String evidencia;
    @Size(max = 400)
    @Column(name = "EJEMPLO")
    private String ejemplo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dbaIdDba", fetch = FetchType.LAZY)
    private List<Piar> piarList;
    @JoinColumn(name = "AREA_DBA", referencedColumnName = "ID_AREA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Area areaDba;
    @JoinColumn(name = "GRADO", referencedColumnName = "ID_GRADO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grado grado;
    @JoinColumn(name = "PERIODO", referencedColumnName = "ID_PERIODO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periodo periodo;

    public Dba() {
    }

    public Dba(Integer idDba) {
        this.idDba = idDba;
    }

    public Integer getIdDba() {
        return idDba;
    }

    public void setIdDba(Integer idDba) {
        this.idDba = idDba;
    }

    public String getObjetivoEnunciado() {
        return objetivoEnunciado;
    }

    public void setObjetivoEnunciado(String objetivoEnunciado) {
        this.objetivoEnunciado = objetivoEnunciado;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    @XmlTransient
    public List<Piar> getPiarList() {
        return piarList;
    }

    public void setPiarList(List<Piar> piarList) {
        this.piarList = piarList;
    }

    public Area getAreaDba() {
        return areaDba;
    }

    public void setAreaDba(Area areaDba) {
        this.areaDba = areaDba;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDba != null ? idDba.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dba)) {
            return false;
        }
        Dba other = (Dba) object;
        if ((this.idDba == null && other.idDba != null) || (this.idDba != null && !this.idDba.equals(other.idDba))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Dba[ idDba=" + idDba + " ]";
    }
    
}
