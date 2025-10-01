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
@Table(name = "estrategias_educativas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstrategiasEducativas.findAll", query = "SELECT e FROM EstrategiasEducativas e"),
    @NamedQuery(name = "EstrategiasEducativas.findByIdEstrategiasEducativas", query = "SELECT e FROM EstrategiasEducativas e WHERE e.idEstrategiasEducativas = :idEstrategiasEducativas"),
    @NamedQuery(name = "EstrategiasEducativas.findByCodigoEstrategiasEducativas", query = "SELECT e FROM EstrategiasEducativas e WHERE e.codigoEstrategiasEducativas = :codigoEstrategiasEducativas"),
    @NamedQuery(name = "EstrategiasEducativas.findByTipoEstrategiasEducativas", query = "SELECT e FROM EstrategiasEducativas e WHERE e.tipoEstrategiasEducativas = :tipoEstrategiasEducativas"),
    @NamedQuery(name = "EstrategiasEducativas.findByDescripcionEstrategia", query = "SELECT e FROM EstrategiasEducativas e WHERE e.descripcionEstrategia = :descripcionEstrategia"),
    @NamedQuery(name = "EstrategiasEducativas.findByHerramientasEstrategia", query = "SELECT e FROM EstrategiasEducativas e WHERE e.herramientasEstrategia = :herramientasEstrategia")})
public class EstrategiasEducativas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTRATEGIAS_EDUCATIVAS")
    private Integer idEstrategiasEducativas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CODIGO_ESTRATEGIAS_EDUCATIVAS")
    private String codigoEstrategiasEducativas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TIPO_ESTRATEGIAS_EDUCATIVAS")
    private String tipoEstrategiasEducativas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "DESCRIPCION_ESTRATEGIA")
    private String descripcionEstrategia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "HERRAMIENTAS_ESTRATEGIA")
    private String herramientasEstrategia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estrategiasEducativasIdEstrategiasEducativas", fetch = FetchType.LAZY)
    private List<PiarHasEstrategiasEducativas> piarHasEstrategiasEducativasList;

    public EstrategiasEducativas() {
    }

    public EstrategiasEducativas(Integer idEstrategiasEducativas) {
        this.idEstrategiasEducativas = idEstrategiasEducativas;
    }

    public EstrategiasEducativas(Integer idEstrategiasEducativas, String codigoEstrategiasEducativas, String tipoEstrategiasEducativas, String descripcionEstrategia, String herramientasEstrategia) {
        this.idEstrategiasEducativas = idEstrategiasEducativas;
        this.codigoEstrategiasEducativas = codigoEstrategiasEducativas;
        this.tipoEstrategiasEducativas = tipoEstrategiasEducativas;
        this.descripcionEstrategia = descripcionEstrategia;
        this.herramientasEstrategia = herramientasEstrategia;
    }

    public Integer getIdEstrategiasEducativas() {
        return idEstrategiasEducativas;
    }

    public void setIdEstrategiasEducativas(Integer idEstrategiasEducativas) {
        this.idEstrategiasEducativas = idEstrategiasEducativas;
    }

    public String getCodigoEstrategiasEducativas() {
        return codigoEstrategiasEducativas;
    }

    public void setCodigoEstrategiasEducativas(String codigoEstrategiasEducativas) {
        this.codigoEstrategiasEducativas = codigoEstrategiasEducativas;
    }

    public String getTipoEstrategiasEducativas() {
        return tipoEstrategiasEducativas;
    }

    public void setTipoEstrategiasEducativas(String tipoEstrategiasEducativas) {
        this.tipoEstrategiasEducativas = tipoEstrategiasEducativas;
    }

    public String getDescripcionEstrategia() {
        return descripcionEstrategia;
    }

    public void setDescripcionEstrategia(String descripcionEstrategia) {
        this.descripcionEstrategia = descripcionEstrategia;
    }

    public String getHerramientasEstrategia() {
        return herramientasEstrategia;
    }

    public void setHerramientasEstrategia(String herramientasEstrategia) {
        this.herramientasEstrategia = herramientasEstrategia;
    }

    @XmlTransient
    public List<PiarHasEstrategiasEducativas> getPiarHasEstrategiasEducativasList() {
        return piarHasEstrategiasEducativasList;
    }

    public void setPiarHasEstrategiasEducativasList(List<PiarHasEstrategiasEducativas> piarHasEstrategiasEducativasList) {
        this.piarHasEstrategiasEducativasList = piarHasEstrategiasEducativasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstrategiasEducativas != null ? idEstrategiasEducativas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrategiasEducativas)) {
            return false;
        }
        EstrategiasEducativas other = (EstrategiasEducativas) object;
        if ((this.idEstrategiasEducativas == null && other.idEstrategiasEducativas != null) || (this.idEstrategiasEducativas != null && !this.idEstrategiasEducativas.equals(other.idEstrategiasEducativas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.EstrategiasEducativas[ idEstrategiasEducativas=" + idEstrategiasEducativas + " ]";
    }
    
}
