/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brandon
 */
@Entity
@Table(name = "estudiante_senal_alerta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudianteSenalAlerta.findAll", query = "SELECT e FROM EstudianteSenalAlerta e"),
    @NamedQuery(name = "EstudianteSenalAlerta.findByIdSenalAlerta", query = "SELECT e FROM EstudianteSenalAlerta e WHERE e.idSenalAlerta = :idSenalAlerta"),
    @NamedQuery(name = "EstudianteSenalAlerta.findByDescripcion", query = "SELECT e FROM EstudianteSenalAlerta e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EstudianteSenalAlerta.findByContextoObservacion", query = "SELECT e FROM EstudianteSenalAlerta e WHERE e.contextoObservacion = :contextoObservacion"),
    @NamedQuery(name = "EstudianteSenalAlerta.findByCreatedAt", query = "SELECT e FROM EstudianteSenalAlerta e WHERE e.createdAt = :createdAt"),
    @NamedQuery(name = "EstudianteSenalAlerta.findByOtraSenal", query = "SELECT e FROM EstudianteSenalAlerta e WHERE e.otraSenal = :otraSenal")})
public class EstudianteSenalAlerta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_senal_alerta")
    private Long idSenalAlerta;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1000)
    @Column(name = "contexto_observacion")
    private String contextoObservacion;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Size(max = 500)
    @Column(name = "otra_senal")
    private String otraSenal;
    @JoinColumn(name = "estudiante_id", referencedColumnName = "ID_ESTUDIANTE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteId;

    public EstudianteSenalAlerta() {
    }

    public EstudianteSenalAlerta(Long idSenalAlerta) {
        this.idSenalAlerta = idSenalAlerta;
    }

    public Long getIdSenalAlerta() {
        return idSenalAlerta;
    }

    public void setIdSenalAlerta(Long idSenalAlerta) {
        this.idSenalAlerta = idSenalAlerta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContextoObservacion() {
        return contextoObservacion;
    }

    public void setContextoObservacion(String contextoObservacion) {
        this.contextoObservacion = contextoObservacion;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getOtraSenal() {
        return otraSenal;
    }

    public void setOtraSenal(String otraSenal) {
        this.otraSenal = otraSenal;
    }

    public Estudiante getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Estudiante estudianteId) {
        this.estudianteId = estudianteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSenalAlerta != null ? idSenalAlerta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteSenalAlerta)) {
            return false;
        }
        EstudianteSenalAlerta other = (EstudianteSenalAlerta) object;
        if ((this.idSenalAlerta == null && other.idSenalAlerta != null) || (this.idSenalAlerta != null && !this.idSenalAlerta.equals(other.idSenalAlerta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entity.EstudianteSenalAlerta[ idSenalAlerta=" + idSenalAlerta + " ]";
    }
    
}
