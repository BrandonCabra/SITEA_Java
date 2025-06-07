/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "matricula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.findByIdMatricula", query = "SELECT m FROM Matricula m WHERE m.idMatricula = :idMatricula"),
    @NamedQuery(name = "Matricula.findByCodigoMatricula", query = "SELECT m FROM Matricula m WHERE m.codigoMatricula = :codigoMatricula"),
    @NamedQuery(name = "Matricula.findByValorMatricula", query = "SELECT m FROM Matricula m WHERE m.valorMatricula = :valorMatricula"),
    @NamedQuery(name = "Matricula.findByFechaMatricula", query = "SELECT m FROM Matricula m WHERE m.fechaMatricula = :fechaMatricula"),
    @NamedQuery(name = "Matricula.findByJornada", query = "SELECT m FROM Matricula m WHERE m.jornada = :jornada")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MATRICULA")
    private Integer idMatricula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CODIGO_MATRICULA")
    private String codigoMatricula;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_MATRICULA")
    private BigDecimal valorMatricula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MATRICULA")
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "JORNADA")
    private String jornada;
    @JoinColumn(name = "FECHA_REGISTRO_ID_FECHA_REGISTRO", referencedColumnName = "ID_FECHA_REGISTRO")
    @ManyToOne(fetch = FetchType.LAZY)
    private FechaRegistro fechaRegistroIdFechaRegistro;
    @JoinColumn(name = "INSTITUCION_ID_INSTITUCION", referencedColumnName = "ID_INSTITUCION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Institucion institucionIdInstitucion;
    @JoinColumn(name = "ESTUDIANTE_ID_ESTUDIANTE", referencedColumnName = "ID_ESTUDIANTE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteIdEstudiante;

    public Matricula() {
    }

    public Matricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Matricula(Integer idMatricula, String codigoMatricula, BigDecimal valorMatricula, Date fechaMatricula, String jornada) {
        this.idMatricula = idMatricula;
        this.codigoMatricula = codigoMatricula;
        this.valorMatricula = valorMatricula;
        this.fechaMatricula = fechaMatricula;
        this.jornada = jornada;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(String codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public BigDecimal getValorMatricula() {
        return valorMatricula;
    }

    public void setValorMatricula(BigDecimal valorMatricula) {
        this.valorMatricula = valorMatricula;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public FechaRegistro getFechaRegistroIdFechaRegistro() {
        return fechaRegistroIdFechaRegistro;
    }

    public void setFechaRegistroIdFechaRegistro(FechaRegistro fechaRegistroIdFechaRegistro) {
        this.fechaRegistroIdFechaRegistro = fechaRegistroIdFechaRegistro;
    }

    public Institucion getInstitucionIdInstitucion() {
        return institucionIdInstitucion;
    }

    public void setInstitucionIdInstitucion(Institucion institucionIdInstitucion) {
        this.institucionIdInstitucion = institucionIdInstitucion;
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
        hash += (idMatricula != null ? idMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.idMatricula == null && other.idMatricula != null) || (this.idMatricula != null && !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Matricula[ idMatricula=" + idMatricula + " ]";
    }
    
}
