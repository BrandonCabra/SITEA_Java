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
import javax.persistence.Lob;
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
 * @author brandon
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
    @NamedQuery(name = "Estudiante.findByAlerta", query = "SELECT e FROM Estudiante e WHERE e.alerta = :alerta"),
    @NamedQuery(name = "Estudiante.findByExpedienteId", query = "SELECT e FROM Estudiante e WHERE e.expedienteId = :expedienteId"),
    @NamedQuery(name = "Estudiante.findByDiagnosticoCertificado", query = "SELECT e FROM Estudiante e WHERE e.diagnosticoCertificado = :diagnosticoCertificado"),
    @NamedQuery(name = "Estudiante.findByTipoTea", query = "SELECT e FROM Estudiante e WHERE e.tipoTea = :tipoTea"),
    @NamedQuery(name = "Estudiante.findByFechaDiagnostico", query = "SELECT e FROM Estudiante e WHERE e.fechaDiagnostico = :fechaDiagnostico"),
    @NamedQuery(name = "Estudiante.findByProfesionalDiagnostico", query = "SELECT e FROM Estudiante e WHERE e.profesionalDiagnostico = :profesionalDiagnostico"),
    @NamedQuery(name = "Estudiante.findByAcudientePrincipal", query = "SELECT e FROM Estudiante e WHERE e.acudientePrincipal = :acudientePrincipal"),
    @NamedQuery(name = "Estudiante.findByRelacionAcudiente", query = "SELECT e FROM Estudiante e WHERE e.relacionAcudiente = :relacionAcudiente"),
    @NamedQuery(name = "Estudiante.findByTelefonoAlternativo", query = "SELECT e FROM Estudiante e WHERE e.telefonoAlternativo = :telefonoAlternativo"),
    @NamedQuery(name = "Estudiante.findByCorreoContacto", query = "SELECT e FROM Estudiante e WHERE e.correoContacto = :correoContacto"),
    @NamedQuery(name = "Estudiante.findByInstitucionProcedencia", query = "SELECT e FROM Estudiante e WHERE e.institucionProcedencia = :institucionProcedencia"),
    @NamedQuery(name = "Estudiante.findByEstadoRegistro", query = "SELECT e FROM Estudiante e WHERE e.estadoRegistro = :estadoRegistro"),
    @NamedQuery(name = "Estudiante.findByFechaRegistro", query = "SELECT e FROM Estudiante e WHERE e.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Estudiante.findByCreatedAt", query = "SELECT e FROM Estudiante e WHERE e.createdAt = :createdAt"),
    @NamedQuery(name = "Estudiante.findByUpdatedAt", query = "SELECT e FROM Estudiante e WHERE e.updatedAt = :updatedAt"),
    @NamedQuery(name = "Estudiante.findByCreatedBy", query = "SELECT e FROM Estudiante e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "Estudiante.findByDocumento", query = "SELECT e FROM Estudiante e WHERE e.tipoDocumentoIdTipoDocumento.idTipoDocumento = :idTipoDocumento AND e.numeroDocumentoEstudiante = :numeroDocumento"),

    @NamedQuery(name = "Estudiante.findByUpdatedBy", query = "SELECT e FROM Estudiante e WHERE e.updatedBy = :updatedBy")})
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
    @Size(max = 1000)
    @Column(name = "alerta")
    private String alerta;
    @Size(max = 30)
    @Column(name = "expediente_id")
    private String expedienteId;
    @Column(name = "diagnostico_certificado")
    private Boolean diagnosticoCertificado;
    @Size(max = 30)
    @Column(name = "tipo_tea")
    private String tipoTea;
    @Column(name = "fecha_diagnostico")
    @Temporal(TemporalType.DATE)
    private Date fechaDiagnostico;
    @Size(max = 150)
    @Column(name = "profesional_diagnostico")
    private String profesionalDiagnostico;
    @Lob
    @Size(max = 65535)
    @Column(name = "observaciones_diagnostico")
    private String observacionesDiagnostico;
    @Lob
    @Size(max = 65535)
    @Column(name = "contexto_observacion")
    private String contextoObservacion;
    @Size(max = 120)
    @Column(name = "acudiente_principal")
    private String acudientePrincipal;
    @Size(max = 20)
    @Column(name = "relacion_acudiente")
    private String relacionAcudiente;
    @Size(max = 15)
    @Column(name = "telefono_alternativo")
    private String telefonoAlternativo;
    @Size(max = 120)
    @Column(name = "correo_contacto")
    private String correoContacto;
    @Size(max = 150)
    @Column(name = "institucion_procedencia")
    private String institucionProcedencia;
    @Size(max = 20)
    @Column(name = "estado_registro")
    private String estadoRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @JoinColumn(name = "CURSO_ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso cursoIdCurso;
    @JoinColumn(name = "TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoDocumento tipoDocumentoIdTipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteId", fetch = FetchType.LAZY)
    private List<EstudianteSenalAlerta> estudianteSenalAlertaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdEstudiante", fetch = FetchType.LAZY)
    private List<EstudianteHasTrastorno> estudianteHasTrastornoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdEstudiante", fetch = FetchType.LAZY)
    private List<Piar> piarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdEstudiante", fetch = FetchType.LAZY)
    private List<BoletinAcademico> boletinAcademicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteId", fetch = FetchType.LAZY)
    private List<EstudianteContacto> estudianteContactoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdEstudiante", fetch = FetchType.LAZY)
    private List<Caracterizacion> caracterizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdEstudiante", fetch = FetchType.LAZY)
    private List<Matricula> matriculaList;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Estudiante(Integer idEstudiante, String numeroDocumentoEstudiante, String primerNombreEstudiante, String primerApellidoEstudiante, Date fechaNacimiento, String direccionEstudiante, String correoInstitucionalEstudiante, Date fechaRegistro, Date createdAt, Date updatedAt) {
        this.idEstudiante = idEstudiante;
        this.numeroDocumentoEstudiante = numeroDocumentoEstudiante;
        this.primerNombreEstudiante = primerNombreEstudiante;
        this.primerApellidoEstudiante = primerApellidoEstudiante;
        this.fechaNacimiento = fechaNacimiento;
        this.direccionEstudiante = direccionEstudiante;
        this.correoInstitucionalEstudiante = correoInstitucionalEstudiante;
        this.fechaRegistro = fechaRegistro;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public String getExpedienteId() {
        return expedienteId;
    }

    public void setExpedienteId(String expedienteId) {
        this.expedienteId = expedienteId;
    }

    public Boolean getDiagnosticoCertificado() {
        return diagnosticoCertificado;
    }

    public void setDiagnosticoCertificado(Boolean diagnosticoCertificado) {
        this.diagnosticoCertificado = diagnosticoCertificado;
    }

    public String getTipoTea() {
        return tipoTea;
    }

    public void setTipoTea(String tipoTea) {
        this.tipoTea = tipoTea;
    }

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getProfesionalDiagnostico() {
        return profesionalDiagnostico;
    }

    public void setProfesionalDiagnostico(String profesionalDiagnostico) {
        this.profesionalDiagnostico = profesionalDiagnostico;
    }

    public String getObservacionesDiagnostico() {
        return observacionesDiagnostico;
    }

    public void setObservacionesDiagnostico(String observacionesDiagnostico) {
        this.observacionesDiagnostico = observacionesDiagnostico;
    }

    public String getContextoObservacion() {
        return contextoObservacion;
    }

    public void setContextoObservacion(String contextoObservacion) {
        this.contextoObservacion = contextoObservacion;
    }

    public String getAcudientePrincipal() {
        return acudientePrincipal;
    }

    public void setAcudientePrincipal(String acudientePrincipal) {
        this.acudientePrincipal = acudientePrincipal;
    }

    public String getRelacionAcudiente() {
        return relacionAcudiente;
    }

    public void setRelacionAcudiente(String relacionAcudiente) {
        this.relacionAcudiente = relacionAcudiente;
    }

    public String getTelefonoAlternativo() {
        return telefonoAlternativo;
    }

    public void setTelefonoAlternativo(String telefonoAlternativo) {
        this.telefonoAlternativo = telefonoAlternativo;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public String getInstitucionProcedencia() {
        return institucionProcedencia;
    }

    public void setInstitucionProcedencia(String institucionProcedencia) {
        this.institucionProcedencia = institucionProcedencia;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Curso getCursoIdCurso() {
        return cursoIdCurso;
    }

    public void setCursoIdCurso(Curso cursoIdCurso) {
        this.cursoIdCurso = cursoIdCurso;
    }

    public TipoDocumento getTipoDocumentoIdTipoDocumento() {
        return tipoDocumentoIdTipoDocumento;
    }

    public void setTipoDocumentoIdTipoDocumento(TipoDocumento tipoDocumentoIdTipoDocumento) {
        this.tipoDocumentoIdTipoDocumento = tipoDocumentoIdTipoDocumento;
    }

    @XmlTransient
    public List<EstudianteSenalAlerta> getEstudianteSenalAlertaList() {
        return estudianteSenalAlertaList;
    }

    public void setEstudianteSenalAlertaList(List<EstudianteSenalAlerta> estudianteSenalAlertaList) {
        this.estudianteSenalAlertaList = estudianteSenalAlertaList;
    }

    @XmlTransient
    public List<EstudianteHasTrastorno> getEstudianteHasTrastornoList() {
        return estudianteHasTrastornoList;
    }

    public void setEstudianteHasTrastornoList(List<EstudianteHasTrastorno> estudianteHasTrastornoList) {
        this.estudianteHasTrastornoList = estudianteHasTrastornoList;
    }

    @XmlTransient
    public List<Piar> getPiarList() {
        return piarList;
    }

    public void setPiarList(List<Piar> piarList) {
        this.piarList = piarList;
    }

    @XmlTransient
    public List<BoletinAcademico> getBoletinAcademicoList() {
        return boletinAcademicoList;
    }

    public void setBoletinAcademicoList(List<BoletinAcademico> boletinAcademicoList) {
        this.boletinAcademicoList = boletinAcademicoList;
    }

    @XmlTransient
    public List<EstudianteContacto> getEstudianteContactoList() {
        return estudianteContactoList;
    }

    public void setEstudianteContactoList(List<EstudianteContacto> estudianteContactoList) {
        this.estudianteContactoList = estudianteContactoList;
    }

    @XmlTransient
    public List<Caracterizacion> getCaracterizacionList() {
        return caracterizacionList;
    }

    public void setCaracterizacionList(List<Caracterizacion> caracterizacionList) {
        this.caracterizacionList = caracterizacionList;
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
        return "com.sena.sitea.entity.Estudiante[ idEstudiante=" + idEstudiante + " ]";
    }
    
}
