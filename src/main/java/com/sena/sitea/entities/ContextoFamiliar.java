/*
 * ContextoFamiliar entity - Almacena información del contexto familiar del estudiante
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad para almacenar información detallada del contexto familiar
 * del estudiante durante la caracterización (RF-008)
 * 
 * @author SITEA
 */
@Entity
@Table(name = "contexto_familiar")
@NamedQueries({
    @NamedQuery(name = "ContextoFamiliar.findAll", query = "SELECT cf FROM ContextoFamiliar cf"),
    @NamedQuery(name = "ContextoFamiliar.findByIdContextoFamiliar", query = "SELECT cf FROM ContextoFamiliar cf WHERE cf.idContextoFamiliar = :idContextoFamiliar"),
    @NamedQuery(name = "ContextoFamiliar.findByCaracterizacion", query = "SELECT cf FROM ContextoFamiliar cf WHERE cf.caracterizacion.idCaracterizacion = :idCaracterizacion")
})
public class ContextoFamiliar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONTEXTO_FAMILIAR")
    private Integer idContextoFamiliar;

    /**
     * Información del acudiente principal
     */
    @Size(max = 120)
    @Column(name = "ACUDIENTE_NOMBRE")
    private String acudienteNombre;

    @Size(max = 20)
    @Column(name = "ACUDIENTE_DOCUMENTO")
    private String acudienteDocumento;

    @Size(max = 20)
    @Column(name = "ACUDIENTE_TELEFONO")
    private String acudienteTelefono;

    @Size(max = 120)
    @Column(name = "ACUDIENTE_EMAIL")
    private String acudienteEmail;

    @Size(max = 45)
    @Column(name = "ACUDIENTE_PARENTESCO")
    private String acudienteParentesco;

    /**
     * Información de la madre
     */
    @Size(max = 120)
    @Column(name = "MADRE_NOMBRE")
    private String madreNombre;

    @Size(max = 20)
    @Column(name = "MADRE_DOCUMENTO")
    private String madreDocumento;

    @Size(max = 20)
    @Column(name = "MADRE_TELEFONO")
    private String madreTelefono;

    @Size(max = 120)
    @Column(name = "MADRE_EMAIL")
    private String madreEmail;

    @Size(max = 150)
    @Column(name = "MADRE_OCUPACION")
    private String madreOcupacion;

    @Size(max = 20)
    @Column(name = "MADRE_ESCOLARIDAD")
    private String madreEscolaridad;

    /**
     * Información del padre
     */
    @Size(max = 120)
    @Column(name = "PADRE_NOMBRE")
    private String padreNombre;

    @Size(max = 20)
    @Column(name = "PADRE_DOCUMENTO")
    private String padreDocumento;

    @Size(max = 20)
    @Column(name = "PADRE_TELEFONO")
    private String padreTelefono;

    @Size(max = 120)
    @Column(name = "PADRE_EMAIL")
    private String padreEmail;

    @Size(max = 150)
    @Column(name = "PADRE_OCUPACION")
    private String padreOcupacion;

    @Size(max = 20)
    @Column(name = "PADRE_ESCOLARIDAD")
    private String padreEscolaridad;

    /**
     * Composición familiar y relaciones
     */
    @Lob
    @Size(max = 65535)
    @Column(name = "OTROS_FAMILIARES")
    private String otrosFamiliares; // Información de otros miembros del hogar

    @Lob
    @Size(max = 65535)
    @Column(name = "RELACIONES_FAMILIARES")
    private String relacionesFamiliares; // Descripción de relaciones y dinámicas familiares

    @Lob
    @Size(max = 65535)
    @Column(name = "COMUNICACION_FAMILIAR")
    private String comunicacionFamiliar; // Canales y efectividad de la comunicación

    /**
     * Vivienda y situación socioeconómica
     */
    @Size(max = 45)
    @Column(name = "TIPO_VIVIENDA")
    private String tipoVivienda; // Casa, apartamento, inquilinato, etc.

    @Size(max = 45)
    @Column(name = "TENENCIA_VIVIENDA")
    private String tenenciaVivienda; // Propia, arrendada, prestada, etc.

    @Lob
    @Size(max = 65535)
    @Column(name = "CONDICIONES_VIVIENDA")
    private String condicionesVivienda; // Servicios públicos, número de habitaciones, etc.

    @Lob
    @Size(max = 65535)
    @Column(name = "SITUACION_ECONOMICA")
    private String situacionEconomica; // Descripción del nivel socioeconómico

    /**
     * Información adicional y observaciones
     */
    @Lob
    @Size(max = 65535)
    @Column(name = "OBSERVACIONES_FAMILIA")
    private String observacionesFamilia;

    /**
     * Relación con la caracterización
     */
    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacion;

    /**
     * Auditoría
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Basic(optional = false)
    @NotNull
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "CREATED_BY")
    private Integer createdBy;

    @Column(name = "UPDATED_BY")
    private Integer updatedBy;

    // Constructores
    public ContextoFamiliar() {
    }

    public ContextoFamiliar(Integer idContextoFamiliar) {
        this.idContextoFamiliar = idContextoFamiliar;
    }

    // Getters y Setters
    public Integer getIdContextoFamiliar() {
        return idContextoFamiliar;
    }

    public void setIdContextoFamiliar(Integer idContextoFamiliar) {
        this.idContextoFamiliar = idContextoFamiliar;
    }

    public String getAcudienteNombre() {
        return acudienteNombre;
    }

    public void setAcudienteNombre(String acudienteNombre) {
        this.acudienteNombre = acudienteNombre;
    }

    public String getAcudienteDocumento() {
        return acudienteDocumento;
    }

    public void setAcudienteDocumento(String acudienteDocumento) {
        this.acudienteDocumento = acudienteDocumento;
    }

    public String getAcudienteTelefono() {
        return acudienteTelefono;
    }

    public void setAcudienteTelefono(String acudienteTelefono) {
        this.acudienteTelefono = acudienteTelefono;
    }

    public String getAcudienteEmail() {
        return acudienteEmail;
    }

    public void setAcudienteEmail(String acudienteEmail) {
        this.acudienteEmail = acudienteEmail;
    }

    public String getAcudienteParentesco() {
        return acudienteParentesco;
    }

    public void setAcudienteParentesco(String acudienteParentesco) {
        this.acudienteParentesco = acudienteParentesco;
    }

    public String getMadreNombre() {
        return madreNombre;
    }

    public void setMadreNombre(String madreNombre) {
        this.madreNombre = madreNombre;
    }

    public String getMadreDocumento() {
        return madreDocumento;
    }

    public void setMadreDocumento(String madreDocumento) {
        this.madreDocumento = madreDocumento;
    }

    public String getMadreTelefono() {
        return madreTelefono;
    }

    public void setMadreTelefono(String madreTelefono) {
        this.madreTelefono = madreTelefono;
    }

    public String getMadreEmail() {
        return madreEmail;
    }

    public void setMadreEmail(String madreEmail) {
        this.madreEmail = madreEmail;
    }

    public String getMadreOcupacion() {
        return madreOcupacion;
    }

    public void setMadreOcupacion(String madreOcupacion) {
        this.madreOcupacion = madreOcupacion;
    }

    public String getMadreEscolaridad() {
        return madreEscolaridad;
    }

    public void setMadreEscolaridad(String madreEscolaridad) {
        this.madreEscolaridad = madreEscolaridad;
    }

    public String getPadreNombre() {
        return padreNombre;
    }

    public void setPadreNombre(String padreNombre) {
        this.padreNombre = padreNombre;
    }

    public String getPadreDocumento() {
        return padreDocumento;
    }

    public void setPadreDocumento(String padreDocumento) {
        this.padreDocumento = padreDocumento;
    }

    public String getPadreTelefono() {
        return padreTelefono;
    }

    public void setPadreTelefono(String padreTelefono) {
        this.padreTelefono = padreTelefono;
    }

    public String getPadreEmail() {
        return padreEmail;
    }

    public void setPadreEmail(String padreEmail) {
        this.padreEmail = padreEmail;
    }

    public String getPadreOcupacion() {
        return padreOcupacion;
    }

    public void setPadreOcupacion(String padreOcupacion) {
        this.padreOcupacion = padreOcupacion;
    }

    public String getPadreEscolaridad() {
        return padreEscolaridad;
    }

    public void setPadreEscolaridad(String padreEscolaridad) {
        this.padreEscolaridad = padreEscolaridad;
    }

    public String getOtrosFamiliares() {
        return otrosFamiliares;
    }

    public void setOtrosFamiliares(String otrosFamiliares) {
        this.otrosFamiliares = otrosFamiliares;
    }

    public String getRelacionesFamiliares() {
        return relacionesFamiliares;
    }

    public void setRelacionesFamiliares(String relacionesFamiliares) {
        this.relacionesFamiliares = relacionesFamiliares;
    }

    public String getComunicacionFamiliar() {
        return comunicacionFamiliar;
    }

    public void setComunicacionFamiliar(String comunicacionFamiliar) {
        this.comunicacionFamiliar = comunicacionFamiliar;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public String getTenenciaVivienda() {
        return tenenciaVivienda;
    }

    public void setTenenciaVivienda(String tenenciaVivienda) {
        this.tenenciaVivienda = tenenciaVivienda;
    }

    public String getCondicionesVivienda() {
        return condicionesVivienda;
    }

    public void setCondicionesVivienda(String condicionesVivienda) {
        this.condicionesVivienda = condicionesVivienda;
    }

    public String getSituacionEconomica() {
        return situacionEconomica;
    }

    public void setSituacionEconomica(String situacionEconomica) {
        this.situacionEconomica = situacionEconomica;
    }

    public String getObservacionesFamilia() {
        return observacionesFamilia;
    }

    public void setObservacionesFamilia(String observacionesFamilia) {
        this.observacionesFamilia = observacionesFamilia;
    }

    public Caracterizacion getCaracterizacion() {
        return caracterizacion;
    }

    public void setCaracterizacion(Caracterizacion caracterizacion) {
        this.caracterizacion = caracterizacion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContextoFamiliar != null ? idContextoFamiliar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContextoFamiliar)) {
            return false;
        }
        ContextoFamiliar other = (ContextoFamiliar) object;
        return (this.idContextoFamiliar != null || other.idContextoFamiliar == null) 
            && (this.idContextoFamiliar == null || this.idContextoFamiliar.equals(other.idContextoFamiliar));
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.ContextoFamiliar[ idContextoFamiliar=" + idContextoFamiliar + " ]";
    }
}
