/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByIdEstudiante", query = "SELECT e FROM Estudiante e WHERE e.idEstudiante = :idEstudiante"),
    @NamedQuery(name = "Estudiante.findByNumeroDocumentoEstudiante", query = "SELECT e FROM Estudiante e WHERE e.numeroDocumentoEstudiante = :numeroDocumentoEstudiante"),
    @NamedQuery(name = "Estudiante.findByPrimerNombreEstudiante", query = "SELECT e FROM Estudiante e WHERE e.primerNombreEstudiante = :primerNombreEstudiante"),
    @NamedQuery(name = "Estudiante.findBySegundoNombreEstudiante", query = "SELECT e FROM Estudiante e WHERE e.segundoNombreEstudiante = :segundoNombreEstudiante"),
    @NamedQuery(name = "Estudiante.findByPrimerApellidoEstudiante", query = "SELECT e FROM Estudiante e WHERE e.primerApellidoEstudiante = :primerApellidoEstudiante"),
    @NamedQuery(name = "Estudiante.findBySegundoApellidoEstudiante", query = "SELECT e FROM Estudiante e WHERE e.segundoApellidoEstudiante = :segundoApellidoEstudiante"),
    @NamedQuery(name = "Estudiante.findByFechaNacimiento", query = "SELECT e FROM Estudiante e WHERE e.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Estudiante.findByTelefonoEstudiante", query = "SELECT e FROM Estudiante e WHERE e.telefonoEstudiante = :telefonoEstudiante"),
    @NamedQuery(name = "Estudiante.findByDireccionEstudiante", query = "SELECT e FROM Estudiante e WHERE e.direccionEstudiante = :direccionEstudiante"),
    @NamedQuery(name = "Estudiante.findByCorreoInstitucionalEstudiante", query = "SELECT e FROM Estudiante e WHERE e.correoInstitucionalEstudiante = :correoInstitucionalEstudiante"),
    @NamedQuery(name = "Estudiante.findByFotografiaEstudiante", query = "SELECT e FROM Estudiante e WHERE e.fotografiaEstudiante = :fotografiaEstudiante"),
    @NamedQuery(name = "Estudiante.findByNumeroDocumentoPadre", query = "SELECT e FROM Estudiante e WHERE e.numeroDocumentoPadre = :numeroDocumentoPadre")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTUDIANTE")
    private Integer idEstudiante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NUMERO_DOCUMENTO_ESTUDIANTE")
    private String numeroDocumentoEstudiante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PRIMER_NOMBRE_ESTUDIANTE")
    private String primerNombreEstudiante;
    @Size(max = 45)
    @Column(name = "SEGUNDO_NOMBRE_ESTUDIANTE")
    private String segundoNombreEstudiante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PRIMER_APELLIDO_ESTUDIANTE")
    private String primerApellidoEstudiante;
    @Size(max = 45)
    @Column(name = "SEGUNDO_APELLIDO_ESTUDIANTE")
    private String segundoApellidoEstudiante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 20)
    @Column(name = "TELEFONO_ESTUDIANTE")
    private String telefonoEstudiante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DIRECCION_ESTUDIANTE")
    private String direccionEstudiante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CORREO_INSTITUCIONAL_ESTUDIANTE")
    private String correoInstitucionalEstudiante;
    @Size(max = 300)
    @Column(name = "FOTOGRAFIA_ESTUDIANTE")
    private String fotografiaEstudiante;
    @Size(max = 45)
    @Column(name = "numero_documento_padre")
    private String numeroDocumentoPadre;
    @JoinColumn(name = "GRADO_ID_GRADO", referencedColumnName = "ID_GRADO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Grado gradoIdGrado;
    @JoinColumn(name = "TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoDocumento tipoDocumentoIdTipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdEstudiante", fetch = FetchType.LAZY)
    private List<BoletinAcademico> boletinAcademicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdEstudiante", fetch = FetchType.LAZY)
    private List<Caracterizacion> caracterizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdEstudiante", fetch = FetchType.LAZY)
    private List<EstudianteHasTrastorno> estudianteHasTrastornoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdEstudiante", fetch = FetchType.LAZY)
    private List<Matricula> matriculaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdEstudiante", fetch = FetchType.LAZY)
    private List<Piar> piarList;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Estudiante(Integer idEstudiante, String numeroDocumentoEstudiante, String primerNombreEstudiante, String primerApellidoEstudiante, Date fechaNacimiento, String direccionEstudiante, String correoInstitucionalEstudiante) {
        this.idEstudiante = idEstudiante;
        this.numeroDocumentoEstudiante = numeroDocumentoEstudiante;
        this.primerNombreEstudiante = primerNombreEstudiante;
        this.primerApellidoEstudiante = primerApellidoEstudiante;
        this.fechaNacimiento = fechaNacimiento;
        this.direccionEstudiante = direccionEstudiante;
        this.correoInstitucionalEstudiante = correoInstitucionalEstudiante;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNumeroDocumentoEstudiante() {
        return numeroDocumentoEstudiante;
    }

    public void setNumeroDocumentoEstudiante(String numeroDocumentoEstudiante) {
        this.numeroDocumentoEstudiante = numeroDocumentoEstudiante;
    }

    public String getPrimerNombreEstudiante() {
        return primerNombreEstudiante;
    }

    public void setPrimerNombreEstudiante(String primerNombreEstudiante) {
        this.primerNombreEstudiante = primerNombreEstudiante;
    }

    public String getSegundoNombreEstudiante() {
        return segundoNombreEstudiante;
    }

    public void setSegundoNombreEstudiante(String segundoNombreEstudiante) {
        this.segundoNombreEstudiante = segundoNombreEstudiante;
    }

    public String getPrimerApellidoEstudiante() {
        return primerApellidoEstudiante;
    }

    public void setPrimerApellidoEstudiante(String primerApellidoEstudiante) {
        this.primerApellidoEstudiante = primerApellidoEstudiante;
    }

    public String getSegundoApellidoEstudiante() {
        return segundoApellidoEstudiante;
    }

    public void setSegundoApellidoEstudiante(String segundoApellidoEstudiante) {
        this.segundoApellidoEstudiante = segundoApellidoEstudiante;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefonoEstudiante() {
        return telefonoEstudiante;
    }

    public void setTelefonoEstudiante(String telefonoEstudiante) {
        this.telefonoEstudiante = telefonoEstudiante;
    }

    public String getDireccionEstudiante() {
        return direccionEstudiante;
    }

    public void setDireccionEstudiante(String direccionEstudiante) {
        this.direccionEstudiante = direccionEstudiante;
    }

    public String getCorreoInstitucionalEstudiante() {
        return correoInstitucionalEstudiante;
    }

    public void setCorreoInstitucionalEstudiante(String correoInstitucionalEstudiante) {
        this.correoInstitucionalEstudiante = correoInstitucionalEstudiante;
    }

    public String getFotografiaEstudiante() {
        return fotografiaEstudiante;
    }

    public void setFotografiaEstudiante(String fotografiaEstudiante) {
        this.fotografiaEstudiante = fotografiaEstudiante;
    }

    public String getNumeroDocumentoPadre() {
        return numeroDocumentoPadre;
    }

    public void setNumeroDocumentoPadre(String numeroDocumentoPadre) {
        this.numeroDocumentoPadre = numeroDocumentoPadre;
    }

    public Grado getGradoIdGrado() {
        return gradoIdGrado;
    }

    public void setGradoIdGrado(Grado gradoIdGrado) {
        this.gradoIdGrado = gradoIdGrado;
    }

    public TipoDocumento getTipoDocumentoIdTipoDocumento() {
        return tipoDocumentoIdTipoDocumento;
    }

    public void setTipoDocumentoIdTipoDocumento(TipoDocumento tipoDocumentoIdTipoDocumento) {
        this.tipoDocumentoIdTipoDocumento = tipoDocumentoIdTipoDocumento;
    }

    @XmlTransient
    public List<BoletinAcademico> getBoletinAcademicoList() {
        return boletinAcademicoList;
    }

    public void setBoletinAcademicoList(List<BoletinAcademico> boletinAcademicoList) {
        this.boletinAcademicoList = boletinAcademicoList;
    }

    @XmlTransient
    public List<Caracterizacion> getCaracterizacionList() {
        return caracterizacionList;
    }

    public void setCaracterizacionList(List<Caracterizacion> caracterizacionList) {
        this.caracterizacionList = caracterizacionList;
    }

    @XmlTransient
    public List<EstudianteHasTrastorno> getEstudianteHasTrastornoList() {
        return estudianteHasTrastornoList;
    }

    public void setEstudianteHasTrastornoList(List<EstudianteHasTrastorno> estudianteHasTrastornoList) {
        this.estudianteHasTrastornoList = estudianteHasTrastornoList;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @XmlTransient
    public List<Piar> getPiarList() {
        return piarList;
    }

    public void setPiarList(List<Piar> piarList) {
        this.piarList = piarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudiante != null ? idEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.idEstudiante == null && other.idEstudiante != null) || (this.idEstudiante != null && !this.idEstudiante.equals(other.idEstudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Estudiante[ idEstudiante=" + idEstudiante + " ]";
    }

    public String getnumero_documento_estudiante() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
