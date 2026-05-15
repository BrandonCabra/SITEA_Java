/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByNumeroDocumento", query = "SELECT u FROM Usuarios u WHERE u.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Usuarios.findByPrimerNombre", query = "SELECT u FROM Usuarios u WHERE u.primerNombre = :primerNombre"),
    @NamedQuery(name = "Usuarios.findByPrimerApellido", query = "SELECT u FROM Usuarios u WHERE u.primerApellido = :primerApellido"),
    @NamedQuery(name = "Usuarios.findByTelefonoUsuario", query = "SELECT u FROM Usuarios u WHERE u.telefonoUsuario = :telefonoUsuario"),
    @NamedQuery(name = "Usuarios.findByDireccionUsuario", query = "SELECT u FROM Usuarios u WHERE u.direccionUsuario = :direccionUsuario"),
    @NamedQuery(name = "Usuarios.findByCorreoUsuario", query = "SELECT u FROM Usuarios u WHERE u.correoUsuario = :correoUsuario"),
    @NamedQuery(name = "Usuarios.findByCorreoInstitucional", query = "SELECT u FROM Usuarios u WHERE u.correoInstitucional = :correoInstitucional"),
    @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
    @NamedQuery(name = "Usuarios.findByFechaRegistroIdFechaRegistro", query = "SELECT u FROM Usuarios u WHERE u.fechaRegistroIdFechaRegistro = :fechaRegistroIdFechaRegistro")})

public class Usuarios implements Serializable {

    @Size(max = 555)
    @Column(name = "FOTO_PERFIL")
    private String fotoPerfil;

    @Size(max = 11)
    @Column(name = "ESTATUS")
    private String estatus;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PRIMER_NOMBRE")
    private String primerNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;
    @Size(max = 45)
    @Column(name = "TELEFONO_USUARIO")
    private String telefonoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECCION_USUARIO")
    private String direccionUsuario;
    @Size(max = 45)
    @Column(name = "CORREO_USUARIO")
    private String correoUsuario;
    @Size(max = 45)
    @Column(name = "CORREO_INSTITUCIONAL")
    private String correoInstitucional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FECHA_REGISTRO_ID_FECHA_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistroIdFechaRegistro;
    @JoinColumn(name = "TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoDocumento tipoDocumentoIdTipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosIdUsuario", fetch = FetchType.LAZY)
    private List<UsuarioprofHasMateria> usuarioprofHasMateriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosIdUsuario", fetch = FetchType.LAZY)
    private List<NovedadesReportes> novedadesReportesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario", fetch = FetchType.LAZY)
    private List<UsuarioRol> usuarioRolList;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String numeroDocumento, String primerNombre, String primerApellido, String direccionUsuario, String password) {
        this.idUsuario = idUsuario;
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.direccionUsuario = direccionUsuario;
        this.password = password;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaRegistroIdFechaRegistro() {
        return fechaRegistroIdFechaRegistro;
    }

    public void setFechaRegistroIdFechaRegistro(Date fechaRegistroIdFechaRegistro) {
        this.fechaRegistroIdFechaRegistro = fechaRegistroIdFechaRegistro;
    }

    public TipoDocumento getTipoDocumentoIdTipoDocumento() {
        return tipoDocumentoIdTipoDocumento;
    }

    public void setTipoDocumentoIdTipoDocumento(TipoDocumento tipoDocumentoIdTipoDocumento) {
        this.tipoDocumentoIdTipoDocumento = tipoDocumentoIdTipoDocumento;
    }

    public Rol getRolIdRol() {
        if (usuarioRolList != null) {
            for (UsuarioRol usuarioRol : usuarioRolList) {
                if (usuarioRol != null && usuarioRol.getRolIdRol() != null) {
                    return usuarioRol.getRolIdRol();
                }
            }
        }
        return null;
    }

    public void setRolIdRol(Rol rolIdRol) {
        if (rolIdRol == null) {
            return;
        }
        if (usuarioRolList == null) {
            usuarioRolList = new ArrayList<>();
        }
        for (UsuarioRol usuarioRol : usuarioRolList) {
            if (usuarioRol != null && usuarioRol.getRolIdRol() != null
                    && Objects.equals(usuarioRol.getRolIdRol().getIdRol(), rolIdRol.getIdRol())) {
                return;
            }
        }
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuarioIdUsuario(this);
        usuarioRol.setRolIdRol(rolIdRol);
        usuarioRolList.add(usuarioRol);
    }

    public void agregarRol(Rol rol) {
        setRolIdRol(rol);
    }

    public void limpiarRoles() {
        if (usuarioRolList != null) {
            usuarioRolList.clear();
        }
    }

    @XmlTransient
    public List<UsuarioprofHasMateria> getUsuarioprofHasMateriaList() {
        return usuarioprofHasMateriaList;
    }

    public void setUsuarioprofHasMateriaList(List<UsuarioprofHasMateria> usuarioprofHasMateriaList) {
        this.usuarioprofHasMateriaList = usuarioprofHasMateriaList;
    }

    @XmlTransient
    public List<NovedadesReportes> getNovedadesReportesList() {
        return novedadesReportesList;
    }

    public void setNovedadesReportesList(List<NovedadesReportes> novedadesReportesList) {
        this.novedadesReportesList = novedadesReportesList;
    }

    @XmlTransient
    public List<UsuarioRol> getUsuarioRolList() {
        return usuarioRolList;
    }

    public void setUsuarioRolList(List<UsuarioRol> usuarioRolList) {
        this.usuarioRolList = usuarioRolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Usuarios[ idUsuario=" + idUsuario + " ]";
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

   

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getPasswordUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setPasswordUsuario(String nuevoHash) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
