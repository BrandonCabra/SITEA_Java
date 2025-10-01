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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brandon
 */
@Entity
@Table(name = "estudiante_contacto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudianteContacto.findAll", query = "SELECT e FROM EstudianteContacto e"),
    @NamedQuery(name = "EstudianteContacto.findByEstudianteContactoId", query = "SELECT e FROM EstudianteContacto e WHERE e.estudianteContactoId = :estudianteContactoId"),
    @NamedQuery(name = "EstudianteContacto.findByNombre", query = "SELECT e FROM EstudianteContacto e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstudianteContacto.findByRelacion", query = "SELECT e FROM EstudianteContacto e WHERE e.relacion = :relacion"),
    @NamedQuery(name = "EstudianteContacto.findByTelefono", query = "SELECT e FROM EstudianteContacto e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "EstudianteContacto.findByEmail", query = "SELECT e FROM EstudianteContacto e WHERE e.email = :email"),
    @NamedQuery(name = "EstudianteContacto.findByAutorizadoLegal", query = "SELECT e FROM EstudianteContacto e WHERE e.autorizadoLegal = :autorizadoLegal"),
    @NamedQuery(name = "EstudianteContacto.findByPreferenciaContacto", query = "SELECT e FROM EstudianteContacto e WHERE e.preferenciaContacto = :preferenciaContacto"),
    @NamedQuery(name = "EstudianteContacto.findByOrdenPrioridad", query = "SELECT e FROM EstudianteContacto e WHERE e.ordenPrioridad = :ordenPrioridad"),
    @NamedQuery(name = "EstudianteContacto.findByCreatedAt", query = "SELECT e FROM EstudianteContacto e WHERE e.createdAt = :createdAt")})
public class EstudianteContacto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "estudiante_contacto_id")
    private Long estudianteContactoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 20)
    @Column(name = "relacion")
    private String relacion;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 120)
    @Column(name = "email")
    private String email;
    @Column(name = "autorizado_legal")
    private Boolean autorizadoLegal;
    @Size(max = 10)
    @Column(name = "preferencia_contacto")
    private String preferenciaContacto;
    @Column(name = "orden_prioridad")
    private Short ordenPrioridad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "estudiante_id", referencedColumnName = "ID_ESTUDIANTE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteId;

    public EstudianteContacto() {
    }

    public EstudianteContacto(Long estudianteContactoId) {
        this.estudianteContactoId = estudianteContactoId;
    }

    public EstudianteContacto(Long estudianteContactoId, String nombre, Date createdAt) {
        this.estudianteContactoId = estudianteContactoId;
        this.nombre = nombre;
        this.createdAt = createdAt;
    }

    public Long getEstudianteContactoId() {
        return estudianteContactoId;
    }

    public void setEstudianteContactoId(Long estudianteContactoId) {
        this.estudianteContactoId = estudianteContactoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAutorizadoLegal() {
        return autorizadoLegal;
    }

    public void setAutorizadoLegal(Boolean autorizadoLegal) {
        this.autorizadoLegal = autorizadoLegal;
    }

    public String getPreferenciaContacto() {
        return preferenciaContacto;
    }

    public void setPreferenciaContacto(String preferenciaContacto) {
        this.preferenciaContacto = preferenciaContacto;
    }

    public Short getOrdenPrioridad() {
        return ordenPrioridad;
    }

    public void setOrdenPrioridad(Short ordenPrioridad) {
        this.ordenPrioridad = ordenPrioridad;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        hash += (estudianteContactoId != null ? estudianteContactoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteContacto)) {
            return false;
        }
        EstudianteContacto other = (EstudianteContacto) object;
        if ((this.estudianteContactoId == null && other.estudianteContactoId != null) || (this.estudianteContactoId != null && !this.estudianteContactoId.equals(other.estudianteContactoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entity.EstudianteContacto[ estudianteContactoId=" + estudianteContactoId + " ]";
    }
    
}
