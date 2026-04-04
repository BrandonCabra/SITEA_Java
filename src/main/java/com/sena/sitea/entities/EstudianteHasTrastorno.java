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
@Table(name = "estudiante_has_trastorno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudianteHasTrastorno.findAll", query = "SELECT e FROM EstudianteHasTrastorno e"),
    @NamedQuery(name = "EstudianteHasTrastorno.findByIDESTUDIANTEhasTRASTORNO", query = "SELECT e FROM EstudianteHasTrastorno e WHERE e.iDESTUDIANTEhasTRASTORNO = :iDESTUDIANTEhasTRASTORNO")})
public class EstudianteHasTrastorno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTUDIANTE_has_TRASTORNO")
    private Integer iDESTUDIANTEhasTRASTORNO;
    @JoinColumn(name = "ESTUDIANTE_ID_ESTUDIANTE", referencedColumnName = "ID_ESTUDIANTE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteIdEstudiante;
    @JoinColumn(name = "TRASTORNO_ID_TRASTORNO", referencedColumnName = "ID_TRASTORNO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Trastorno trastornoIdTrastorno;

    public EstudianteHasTrastorno() {
    }

    public EstudianteHasTrastorno(Integer iDESTUDIANTEhasTRASTORNO) {
        this.iDESTUDIANTEhasTRASTORNO = iDESTUDIANTEhasTRASTORNO;
    }

    public Integer getIDESTUDIANTEhasTRASTORNO() {
        return iDESTUDIANTEhasTRASTORNO;
    }

    public void setIDESTUDIANTEhasTRASTORNO(Integer iDESTUDIANTEhasTRASTORNO) {
        this.iDESTUDIANTEhasTRASTORNO = iDESTUDIANTEhasTRASTORNO;
    }

    public Estudiante getEstudianteIdEstudiante() {
        return estudianteIdEstudiante;
    }

    public void setEstudianteIdEstudiante(Estudiante estudianteIdEstudiante) {
        this.estudianteIdEstudiante = estudianteIdEstudiante;
    }

    public Trastorno getTrastornoIdTrastorno() {
        return trastornoIdTrastorno;
    }

    public void setTrastornoIdTrastorno(Trastorno trastornoIdTrastorno) {
        this.trastornoIdTrastorno = trastornoIdTrastorno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDESTUDIANTEhasTRASTORNO != null ? iDESTUDIANTEhasTRASTORNO.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteHasTrastorno)) {
            return false;
        }
        EstudianteHasTrastorno other = (EstudianteHasTrastorno) object;
        if ((this.iDESTUDIANTEhasTRASTORNO == null && other.iDESTUDIANTEhasTRASTORNO != null) || (this.iDESTUDIANTEhasTRASTORNO != null && !this.iDESTUDIANTEhasTRASTORNO.equals(other.iDESTUDIANTEhasTRASTORNO))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.EstudianteHasTrastorno[ iDESTUDIANTEhasTRASTORNO=" + iDESTUDIANTEhasTRASTORNO + " ]";
    }
    
}
