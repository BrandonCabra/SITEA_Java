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
@Table(name = "trastorno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trastorno.findAll", query = "SELECT t FROM Trastorno t"),
    @NamedQuery(name = "Trastorno.findByIdTrastorno", query = "SELECT t FROM Trastorno t WHERE t.idTrastorno = :idTrastorno"),
    @NamedQuery(name = "Trastorno.findByNombreTrastorno", query = "SELECT t FROM Trastorno t WHERE t.nombreTrastorno = :nombreTrastorno"),
    @NamedQuery(name = "Trastorno.findByDescripci\u00f3nTrastorno", query = "SELECT t FROM Trastorno t WHERE t.descripci\u00f3nTrastorno = :descripci\u00f3nTrastorno"),
    @NamedQuery(name = "Trastorno.findByDiagnosticoMedicoTrastorno", query = "SELECT t FROM Trastorno t WHERE t.diagnosticoMedicoTrastorno = :diagnosticoMedicoTrastorno")})
public class Trastorno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TRASTORNO")
    private Integer idTrastorno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_TRASTORNO")
    private String nombreTrastorno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DESCRIPCI\u00d3N_TRASTORNO")
    private String descripciónTrastorno;
    @Size(max = 200)
    @Column(name = "DIAGNOSTICO_MEDICO_TRASTORNO")
    private String diagnosticoMedicoTrastorno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trastornoIdTrastorno", fetch = FetchType.LAZY)
    private List<EstudianteHasTrastorno> estudianteHasTrastornoList;

    public Trastorno() {
    }

    public Trastorno(Integer idTrastorno) {
        this.idTrastorno = idTrastorno;
    }

    public Trastorno(Integer idTrastorno, String nombreTrastorno, String descripciónTrastorno) {
        this.idTrastorno = idTrastorno;
        this.nombreTrastorno = nombreTrastorno;
        this.descripciónTrastorno = descripciónTrastorno;
    }

    public Integer getIdTrastorno() {
        return idTrastorno;
    }

    public void setIdTrastorno(Integer idTrastorno) {
        this.idTrastorno = idTrastorno;
    }

    public String getNombreTrastorno() {
        return nombreTrastorno;
    }

    public void setNombreTrastorno(String nombreTrastorno) {
        this.nombreTrastorno = nombreTrastorno;
    }

    public String getDescripciónTrastorno() {
        return descripciónTrastorno;
    }

    public void setDescripciónTrastorno(String descripciónTrastorno) {
        this.descripciónTrastorno = descripciónTrastorno;
    }

    public String getDiagnosticoMedicoTrastorno() {
        return diagnosticoMedicoTrastorno;
    }

    public void setDiagnosticoMedicoTrastorno(String diagnosticoMedicoTrastorno) {
        this.diagnosticoMedicoTrastorno = diagnosticoMedicoTrastorno;
    }

    @XmlTransient
    public List<EstudianteHasTrastorno> getEstudianteHasTrastornoList() {
        return estudianteHasTrastornoList;
    }

    public void setEstudianteHasTrastornoList(List<EstudianteHasTrastorno> estudianteHasTrastornoList) {
        this.estudianteHasTrastornoList = estudianteHasTrastornoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrastorno != null ? idTrastorno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trastorno)) {
            return false;
        }
        Trastorno other = (Trastorno) object;
        if ((this.idTrastorno == null && other.idTrastorno != null) || (this.idTrastorno != null && !this.idTrastorno.equals(other.idTrastorno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Trastorno[ idTrastorno=" + idTrastorno + " ]";
    }
    
}
