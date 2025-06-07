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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "piar_has_estrategias_educativas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PiarHasEstrategiasEducativas.findAll", query = "SELECT p FROM PiarHasEstrategiasEducativas p"),
    @NamedQuery(name = "PiarHasEstrategiasEducativas.findByCODIGOPIARhasESTRATEGIASEDUCATIVAS", query = "SELECT p FROM PiarHasEstrategiasEducativas p WHERE p.cODIGOPIARhasESTRATEGIASEDUCATIVAS = :cODIGOPIARhasESTRATEGIASEDUCATIVAS"),
    @NamedQuery(name = "PiarHasEstrategiasEducativas.findByEvaluaci\u00f3nEstrategia", query = "SELECT p FROM PiarHasEstrategiasEducativas p WHERE p.evaluaci\u00f3nEstrategia = :evaluaci\u00f3nEstrategia")})
public class PiarHasEstrategiasEducativas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODIGO_PIAR_has_ESTRATEGIAS_EDUCATIVAS")
    private Integer cODIGOPIARhasESTRATEGIASEDUCATIVAS;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "EVALUACI\u00d3N_ESTRATEGIA")
    private String evaluaciónEstrategia;
    @JoinColumn(name = "PIAR_ID_PIAR", referencedColumnName = "ID_PIAR")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Piar piarIdPiar;
    @JoinColumn(name = "ESTRATEGIAS_EDUCATIVAS_ID_ESTRATEGIAS_EDUCATIVAS", referencedColumnName = "ID_ESTRATEGIAS_EDUCATIVAS")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstrategiasEducativas estrategiasEducativasIdEstrategiasEducativas;

    public PiarHasEstrategiasEducativas() {
    }

    public PiarHasEstrategiasEducativas(Integer cODIGOPIARhasESTRATEGIASEDUCATIVAS) {
        this.cODIGOPIARhasESTRATEGIASEDUCATIVAS = cODIGOPIARhasESTRATEGIASEDUCATIVAS;
    }

    public PiarHasEstrategiasEducativas(Integer cODIGOPIARhasESTRATEGIASEDUCATIVAS, String evaluaciónEstrategia) {
        this.cODIGOPIARhasESTRATEGIASEDUCATIVAS = cODIGOPIARhasESTRATEGIASEDUCATIVAS;
        this.evaluaciónEstrategia = evaluaciónEstrategia;
    }

    public Integer getCODIGOPIARhasESTRATEGIASEDUCATIVAS() {
        return cODIGOPIARhasESTRATEGIASEDUCATIVAS;
    }

    public void setCODIGOPIARhasESTRATEGIASEDUCATIVAS(Integer cODIGOPIARhasESTRATEGIASEDUCATIVAS) {
        this.cODIGOPIARhasESTRATEGIASEDUCATIVAS = cODIGOPIARhasESTRATEGIASEDUCATIVAS;
    }

    public String getEvaluaciónEstrategia() {
        return evaluaciónEstrategia;
    }

    public void setEvaluaciónEstrategia(String evaluaciónEstrategia) {
        this.evaluaciónEstrategia = evaluaciónEstrategia;
    }

    public Piar getPiarIdPiar() {
        return piarIdPiar;
    }

    public void setPiarIdPiar(Piar piarIdPiar) {
        this.piarIdPiar = piarIdPiar;
    }

    public EstrategiasEducativas getEstrategiasEducativasIdEstrategiasEducativas() {
        return estrategiasEducativasIdEstrategiasEducativas;
    }

    public void setEstrategiasEducativasIdEstrategiasEducativas(EstrategiasEducativas estrategiasEducativasIdEstrategiasEducativas) {
        this.estrategiasEducativasIdEstrategiasEducativas = estrategiasEducativasIdEstrategiasEducativas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cODIGOPIARhasESTRATEGIASEDUCATIVAS != null ? cODIGOPIARhasESTRATEGIASEDUCATIVAS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PiarHasEstrategiasEducativas)) {
            return false;
        }
        PiarHasEstrategiasEducativas other = (PiarHasEstrategiasEducativas) object;
        if ((this.cODIGOPIARhasESTRATEGIASEDUCATIVAS == null && other.cODIGOPIARhasESTRATEGIASEDUCATIVAS != null) || (this.cODIGOPIARhasESTRATEGIASEDUCATIVAS != null && !this.cODIGOPIARhasESTRATEGIASEDUCATIVAS.equals(other.cODIGOPIARhasESTRATEGIASEDUCATIVAS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.PiarHasEstrategiasEducativas[ cODIGOPIARhasESTRATEGIASEDUCATIVAS=" + cODIGOPIARhasESTRATEGIASEDUCATIVAS + " ]";
    }
    
}
