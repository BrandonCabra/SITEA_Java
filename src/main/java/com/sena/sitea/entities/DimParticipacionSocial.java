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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "dim_participacion_social")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DimParticipacionSocial.findAll", query = "SELECT d FROM DimParticipacionSocial d"),
    @NamedQuery(name = "DimParticipacionSocial.findByCaracterizacion", query = "SELECT d FROM DimParticipacionSocial d WHERE d.caracterizacionId = :caracterizacionId")
})
public class DimParticipacionSocial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIM_PARTICIPACION")
    private Integer idDimParticipacion;

    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacionId;

    @Column(name = "ACEPTACION_COMPANEROS")
    private Integer aceptacionCompaneros;

    @Column(name = "GRUPO_AMIGOS")
    private Integer grupoAmigos;

    @Column(name = "PARTICIPACION_DEBATES")
    private Integer participacionDebates;

    @Column(name = "PEDIR_AYUDA")
    private Integer pedirAyuda;

    @Column(name = "RESPETO_DIVERSIDAD")
    private Integer respetoDiversidad;

    @Column(name = "ACTIVIDADES_EXTRACURRICULARES")
    private Integer actividadesExtracurriculares;

    @Column(name = "OPORTUNIDADES_PARTICIPACION")
    private Integer oportunidadesParticipacion;

    @Column(name = "ACCESIBILIDAD_INSTALACIONES")
    private Integer accesibilidadInstalaciones;

    @Column(name = "RECURSOS_COMUNITARIOS")
    private Integer recursosComunitarios;

    @Column(name = "VALORACION_OPINION")
    private Integer valoracionOpinion;

    @Column(name = "PROMOCION_INCLUSION")
    private Integer promocionInclusion;

    @Column(name = "RUTA_ATENCION_EXCLUSION")
    private Integer rutaAtencionExclusion;

    @Column(name = "SEGURIDAD_ESCOLAR")
    private Integer seguridadEscolar;

    @Column(name = "ASERTIVIDAD")
    private Integer asertividad;

    @Column(name = "EMPATIA")
    private Integer empatia;

    @Lob
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    public DimParticipacionSocial() { }

    public DimParticipacionSocial(Integer idDimParticipacion) { this.idDimParticipacion = idDimParticipacion; }

    public Integer getIdDimParticipacion() { return idDimParticipacion; }
    public void setIdDimParticipacion(Integer idDimParticipacion) { this.idDimParticipacion = idDimParticipacion; }

    public Caracterizacion getCaracterizacionId() { return caracterizacionId; }
    public void setCaracterizacionId(Caracterizacion caracterizacionId) { this.caracterizacionId = caracterizacionId; }

    public Integer getAceptacionCompaneros() { return aceptacionCompaneros; }
    public void setAceptacionCompaneros(Integer aceptacionCompaneros) { this.aceptacionCompaneros = aceptacionCompaneros; }

    public Integer getGrupoAmigos() { return grupoAmigos; }
    public void setGrupoAmigos(Integer grupoAmigos) { this.grupoAmigos = grupoAmigos; }

    public Integer getParticipacionDebates() { return participacionDebates; }
    public void setParticipacionDebates(Integer participacionDebates) { this.participacionDebates = participacionDebates; }

    public Integer getPedirAyuda() { return pedirAyuda; }
    public void setPedirAyuda(Integer pedirAyuda) { this.pedirAyuda = pedirAyuda; }

    public Integer getRespetoDiversidad() { return respetoDiversidad; }
    public void setRespetoDiversidad(Integer respetoDiversidad) { this.respetoDiversidad = respetoDiversidad; }

    public Integer getActividadesExtracurriculares() { return actividadesExtracurriculares; }
    public void setActividadesExtracurriculares(Integer actividadesExtracurriculares) { this.actividadesExtracurriculares = actividadesExtracurriculares; }

    public Integer getOportunidadesParticipacion() { return oportunidadesParticipacion; }
    public void setOportunidadesParticipacion(Integer oportunidadesParticipacion) { this.oportunidadesParticipacion = oportunidadesParticipacion; }

    public Integer getAccesibilidadInstalaciones() { return accesibilidadInstalaciones; }
    public void setAccesibilidadInstalaciones(Integer accesibilidadInstalaciones) { this.accesibilidadInstalaciones = accesibilidadInstalaciones; }

    public Integer getRecursosComunitarios() { return recursosComunitarios; }
    public void setRecursosComunitarios(Integer recursosComunitarios) { this.recursosComunitarios = recursosComunitarios; }

    public Integer getValoracionOpinion() { return valoracionOpinion; }
    public void setValoracionOpinion(Integer valoracionOpinion) { this.valoracionOpinion = valoracionOpinion; }

    public Integer getPromocionInclusion() { return promocionInclusion; }
    public void setPromocionInclusion(Integer promocionInclusion) { this.promocionInclusion = promocionInclusion; }

    public Integer getRutaAtencionExclusion() { return rutaAtencionExclusion; }
    public void setRutaAtencionExclusion(Integer rutaAtencionExclusion) { this.rutaAtencionExclusion = rutaAtencionExclusion; }

    public Integer getSeguridadEscolar() { return seguridadEscolar; }
    public void setSeguridadEscolar(Integer seguridadEscolar) { this.seguridadEscolar = seguridadEscolar; }

    public Integer getAsertividad() { return asertividad; }
    public void setAsertividad(Integer asertividad) { this.asertividad = asertividad; }

    public Integer getEmpatia() { return empatia; }
    public void setEmpatia(Integer empatia) { this.empatia = empatia; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
