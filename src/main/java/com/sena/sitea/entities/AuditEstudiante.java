/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brandon
 */
@Entity
@Table(name = "audit_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditEstudiante.findAll", query = "SELECT a FROM AuditEstudiante a"),
    @NamedQuery(name = "AuditEstudiante.findByAuditId", query = "SELECT a FROM AuditEstudiante a WHERE a.auditId = :auditId"),
    @NamedQuery(name = "AuditEstudiante.findByEstudianteId", query = "SELECT a FROM AuditEstudiante a WHERE a.estudianteId = :estudianteId"),
    @NamedQuery(name = "AuditEstudiante.findByOperationType", query = "SELECT a FROM AuditEstudiante a WHERE a.operationType = :operationType"),
    @NamedQuery(name = "AuditEstudiante.findByOldEstadoRegistro", query = "SELECT a FROM AuditEstudiante a WHERE a.oldEstadoRegistro = :oldEstadoRegistro"),
    @NamedQuery(name = "AuditEstudiante.findByNewEstadoRegistro", query = "SELECT a FROM AuditEstudiante a WHERE a.newEstadoRegistro = :newEstadoRegistro"),
    @NamedQuery(name = "AuditEstudiante.findByOldExpedienteId", query = "SELECT a FROM AuditEstudiante a WHERE a.oldExpedienteId = :oldExpedienteId"),
    @NamedQuery(name = "AuditEstudiante.findByNewExpedienteId", query = "SELECT a FROM AuditEstudiante a WHERE a.newExpedienteId = :newExpedienteId"),
    @NamedQuery(name = "AuditEstudiante.findByChangedBy", query = "SELECT a FROM AuditEstudiante a WHERE a.changedBy = :changedBy"),
    @NamedQuery(name = "AuditEstudiante.findByChangedAt", query = "SELECT a FROM AuditEstudiante a WHERE a.changedAt = :changedAt")})
public class AuditEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "audit_id")
    private Long auditId;
    @Column(name = "estudiante_id")
    private BigInteger estudianteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "operation_type")
    private Character operationType;
    @Size(max = 20)
    @Column(name = "old_estado_registro")
    private String oldEstadoRegistro;
    @Size(max = 20)
    @Column(name = "new_estado_registro")
    private String newEstadoRegistro;
    @Size(max = 30)
    @Column(name = "old_expediente_id")
    private String oldExpedienteId;
    @Size(max = 30)
    @Column(name = "new_expediente_id")
    private String newExpedienteId;
    @Column(name = "changed_by")
    private Integer changedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "changed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedAt;

    public AuditEstudiante() {
    }

    public AuditEstudiante(Long auditId) {
        this.auditId = auditId;
    }

    public AuditEstudiante(Long auditId, Character operationType, Date changedAt) {
        this.auditId = auditId;
        this.operationType = operationType;
        this.changedAt = changedAt;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public BigInteger getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(BigInteger estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Character getOperationType() {
        return operationType;
    }

    public void setOperationType(Character operationType) {
        this.operationType = operationType;
    }

    public String getOldEstadoRegistro() {
        return oldEstadoRegistro;
    }

    public void setOldEstadoRegistro(String oldEstadoRegistro) {
        this.oldEstadoRegistro = oldEstadoRegistro;
    }

    public String getNewEstadoRegistro() {
        return newEstadoRegistro;
    }

    public void setNewEstadoRegistro(String newEstadoRegistro) {
        this.newEstadoRegistro = newEstadoRegistro;
    }

    public String getOldExpedienteId() {
        return oldExpedienteId;
    }

    public void setOldExpedienteId(String oldExpedienteId) {
        this.oldExpedienteId = oldExpedienteId;
    }

    public String getNewExpedienteId() {
        return newExpedienteId;
    }

    public void setNewExpedienteId(String newExpedienteId) {
        this.newExpedienteId = newExpedienteId;
    }

    public Integer getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(Integer changedBy) {
        this.changedBy = changedBy;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auditId != null ? auditId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditEstudiante)) {
            return false;
        }
        AuditEstudiante other = (AuditEstudiante) object;
        if ((this.auditId == null && other.auditId != null) || (this.auditId != null && !this.auditId.equals(other.auditId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.AuditEstudiante[ auditId=" + auditId + " ]";
    }
    
}
