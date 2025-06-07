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
@Table(name = "caracterizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caracterizacion.findAll", query = "SELECT c FROM Caracterizacion c"),
    @NamedQuery(name = "Caracterizacion.findByIdCaracterizacion", query = "SELECT c FROM Caracterizacion c WHERE c.idCaracterizacion = :idCaracterizacion"),
    @NamedQuery(name = "Caracterizacion.findByCodigoCaracterizacion", query = "SELECT c FROM Caracterizacion c WHERE c.codigoCaracterizacion = :codigoCaracterizacion"),
    @NamedQuery(name = "Caracterizacion.findByContextoAcademico", query = "SELECT c FROM Caracterizacion c WHERE c.contextoAcademico = :contextoAcademico"),
    @NamedQuery(name = "Caracterizacion.findByContextoFamiliar", query = "SELECT c FROM Caracterizacion c WHERE c.contextoFamiliar = :contextoFamiliar"),
    @NamedQuery(name = "Caracterizacion.findByContextoEscolar", query = "SELECT c FROM Caracterizacion c WHERE c.contextoEscolar = :contextoEscolar"),
    @NamedQuery(name = "Caracterizacion.findByDiagnostico", query = "SELECT c FROM Caracterizacion c WHERE c.diagnostico = :diagnostico"),
    @NamedQuery(name = "Caracterizacion.findByValoracionPedagogica", query = "SELECT c FROM Caracterizacion c WHERE c.valoracionPedagogica = :valoracionPedagogica"),
    @NamedQuery(name = "Caracterizacion.findByBarraDeAprendizaje", query = "SELECT c FROM Caracterizacion c WHERE c.barraDeAprendizaje = :barraDeAprendizaje"),
    @NamedQuery(name = "Caracterizacion.findByRecomendaciones", query = "SELECT c FROM Caracterizacion c WHERE c.recomendaciones = :recomendaciones"),
    @NamedQuery(name = "Caracterizacion.findByCorresponsabilidad", query = "SELECT c FROM Caracterizacion c WHERE c.corresponsabilidad = :corresponsabilidad")})
public class Caracterizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CARACTERIZACION")
    private Integer idCaracterizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CODIGO_CARACTERIZACION")
    private String codigoCaracterizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CONTEXTO_ACADEMICO")
    private String contextoAcademico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CONTEXTO_FAMILIAR")
    private String contextoFamiliar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CONTEXTO_ESCOLAR")
    private String contextoEscolar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DIAGNOSTICO")
    private String diagnostico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "VALORACION_PEDAGOGICA")
    private String valoracionPedagogica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "BARRA_DE_APRENDIZAJE")
    private String barraDeAprendizaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "RECOMENDACIONES")
    private String recomendaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CORRESPONSABILIDAD")
    private String corresponsabilidad;
    @JoinColumn(name = "ESTUDIANTE_ID_ESTUDIANTE", referencedColumnName = "ID_ESTUDIANTE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteIdEstudiante;

    public Caracterizacion() {
    }

    public Caracterizacion(Integer idCaracterizacion) {
        this.idCaracterizacion = idCaracterizacion;
    }

    public Caracterizacion(Integer idCaracterizacion, String codigoCaracterizacion, String contextoAcademico, String contextoFamiliar, String contextoEscolar, String diagnostico, String valoracionPedagogica, String barraDeAprendizaje, String recomendaciones, String corresponsabilidad) {
        this.idCaracterizacion = idCaracterizacion;
        this.codigoCaracterizacion = codigoCaracterizacion;
        this.contextoAcademico = contextoAcademico;
        this.contextoFamiliar = contextoFamiliar;
        this.contextoEscolar = contextoEscolar;
        this.diagnostico = diagnostico;
        this.valoracionPedagogica = valoracionPedagogica;
        this.barraDeAprendizaje = barraDeAprendizaje;
        this.recomendaciones = recomendaciones;
        this.corresponsabilidad = corresponsabilidad;
    }

    public Integer getIdCaracterizacion() {
        return idCaracterizacion;
    }

    public void setIdCaracterizacion(Integer idCaracterizacion) {
        this.idCaracterizacion = idCaracterizacion;
    }

    public String getCodigoCaracterizacion() {
        return codigoCaracterizacion;
    }

    public void setCodigoCaracterizacion(String codigoCaracterizacion) {
        this.codigoCaracterizacion = codigoCaracterizacion;
    }

    public String getContextoAcademico() {
        return contextoAcademico;
    }

    public void setContextoAcademico(String contextoAcademico) {
        this.contextoAcademico = contextoAcademico;
    }

    public String getContextoFamiliar() {
        return contextoFamiliar;
    }

    public void setContextoFamiliar(String contextoFamiliar) {
        this.contextoFamiliar = contextoFamiliar;
    }

    public String getContextoEscolar() {
        return contextoEscolar;
    }

    public void setContextoEscolar(String contextoEscolar) {
        this.contextoEscolar = contextoEscolar;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getValoracionPedagogica() {
        return valoracionPedagogica;
    }

    public void setValoracionPedagogica(String valoracionPedagogica) {
        this.valoracionPedagogica = valoracionPedagogica;
    }

    public String getBarraDeAprendizaje() {
        return barraDeAprendizaje;
    }

    public void setBarraDeAprendizaje(String barraDeAprendizaje) {
        this.barraDeAprendizaje = barraDeAprendizaje;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getCorresponsabilidad() {
        return corresponsabilidad;
    }

    public void setCorresponsabilidad(String corresponsabilidad) {
        this.corresponsabilidad = corresponsabilidad;
    }

    public Estudiante getEstudianteIdEstudiante() {
        return estudianteIdEstudiante;
    }

    public void setEstudianteIdEstudiante(Estudiante estudianteIdEstudiante) {
        this.estudianteIdEstudiante = estudianteIdEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaracterizacion != null ? idCaracterizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caracterizacion)) {
            return false;
        }
        Caracterizacion other = (Caracterizacion) object;
        if ((this.idCaracterizacion == null && other.idCaracterizacion != null) || (this.idCaracterizacion != null && !this.idCaracterizacion.equals(other.idCaracterizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Caracterizacion[ idCaracterizacion=" + idCaracterizacion + " ]";
    }
    
}
