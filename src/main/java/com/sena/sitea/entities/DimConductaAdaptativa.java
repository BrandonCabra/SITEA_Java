package com.sena.sitea.entities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "dim_conducta_adaptativa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DimConductaAdaptativa.findAll", query = "SELECT d FROM DimConductaAdaptativa d"),
    @NamedQuery(name = "DimConductaAdaptativa.findByCaracterizacion", query = "SELECT d FROM DimConductaAdaptativa d WHERE d.caracterizacionId = :caracterizacionId")
})
public class DimConductaAdaptativa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIM_ADAPTATIVA")
    private Integer idDimAdaptativa;

    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacionId;

    @Column(name = "ALIMENTACION")
    private Integer alimentacion;
    @Column(name = "HIGIENE")
    private Integer higiene;
    @Column(name = "VESTIDO")
    private Integer vestido;
    @Column(name = "SEGURIDAD")
    private Integer seguridad;
    @Column(name = "COMUNICACION")
    private Integer comunicacion;
    @Column(name = "SEGUIMIENTO_INSTRUCCIONES")
    private Integer seguimientoInstrucciones;
    @Column(name = "MANEJO_DINERO_TIEMPO")
    private Integer manejoDineroTiempo;
    @Column(name = "LECTURA_FUNCIONAL")
    private Integer lecturaFuncional;
    @Column(name = "INTERACCION_PARES")
    private Integer interaccionPares;
    @Column(name = "RESPETO_NORMAS")
    private Integer respetoNormas;
    @Column(name = "MANEJO_CAMBIOS")
    private Integer manejoCambios;
    @Column(name = "AUTOCONTROL")
    private Integer autocontrol;

    @Lob
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    public DimConductaAdaptativa() { }

    public DimConductaAdaptativa(Integer idDimAdaptativa) { this.idDimAdaptativa = idDimAdaptativa; }

    public Integer getIdDimAdaptativa() { return idDimAdaptativa; }
    public void setIdDimAdaptativa(Integer idDimAdaptativa) { this.idDimAdaptativa = idDimAdaptativa; }

    public Caracterizacion getCaracterizacionId() { return caracterizacionId; }
    public void setCaracterizacionId(Caracterizacion caracterizacionId) { this.caracterizacionId = caracterizacionId; }

    public Integer getAlimentacion() { return alimentacion; }
    public void setAlimentacion(Integer alimentacion) { this.alimentacion = alimentacion; }

    public Integer getHigiene() { return higiene; }
    public void setHigiene(Integer higiene) { this.higiene = higiene; }

    public Integer getVestido() { return vestido; }
    public void setVestido(Integer vestido) { this.vestido = vestido; }

    public Integer getSeguridad() { return seguridad; }
    public void setSeguridad(Integer seguridad) { this.seguridad = seguridad; }

    public Integer getComunicacion() { return comunicacion; }
    public void setComunicacion(Integer comunicacion) { this.comunicacion = comunicacion; }

    public Integer getSeguimientoInstrucciones() { return seguimientoInstrucciones; }
    public void setSeguimientoInstrucciones(Integer seguimientoInstrucciones) { this.seguimientoInstrucciones = seguimientoInstrucciones; }

    public Integer getManejoDineroTiempo() { return manejoDineroTiempo; }
    public void setManejoDineroTiempo(Integer manejoDineroTiempo) { this.manejoDineroTiempo = manejoDineroTiempo; }

    public Integer getLecturaFuncional() { return lecturaFuncional; }
    public void setLecturaFuncional(Integer lecturaFuncional) { this.lecturaFuncional = lecturaFuncional; }

    public Integer getInteraccionPares() { return interaccionPares; }
    public void setInteraccionPares(Integer interaccionPares) { this.interaccionPares = interaccionPares; }

    public Integer getRespetoNormas() { return respetoNormas; }
    public void setRespetoNormas(Integer respetoNormas) { this.respetoNormas = respetoNormas; }

    public Integer getManejoCambios() { return manejoCambios; }
    public void setManejoCambios(Integer manejoCambios) { this.manejoCambios = manejoCambios; }

    public Integer getAutocontrol() { return autocontrol; }
    public void setAutocontrol(Integer autocontrol) { this.autocontrol = autocontrol; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
