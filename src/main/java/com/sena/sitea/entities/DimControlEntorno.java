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
@Table(name = "dim_control_entorno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DimControlEntorno.findAll", query = "SELECT d FROM DimControlEntorno d"),
    @NamedQuery(name = "DimControlEntorno.findByCaracterizacion", query = "SELECT d FROM DimControlEntorno d WHERE d.caracterizacionId = :caracterizacionId")
})
public class DimControlEntorno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIM_CONTROL")
    private Integer idDimControl;

    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacionId;

    @Column(name = "RECONOCE_INTERESES")
    private Integer reconoceIntereses;
    @Column(name = "TIENE_METAS")
    private Integer tieneMetas;
    @Column(name = "PLANES_REFLEJAN_DESEOS")
    private Integer planesReflejanDeseos;
    @Column(name = "IDENTIFICA_FORTALEZAS")
    private Integer identificaFortalezas;
    @Column(name = "SEGURIDAD_DECISIONES")
    private Integer seguridadDecisiones;
    @Column(name = "DECIDE_LUGARES")
    private Integer decideLugares;
    @Column(name = "REQUIERE_POCO_APOYO")
    private Integer requierePocoApoyo;
    @Column(name = "CONSCIENTE_CONSECUENCIAS")
    private Integer conscienteConsecuencias;
    @Column(name = "LIBERTAD_EXPRESION")
    private Integer libertadExpresion;
    @Column(name = "FAMILIA_RESPETA_DECISIONES")
    private Integer familiaRespetaDecisiones;
    @Column(name = "CONOCE_DERECHOS")
    private Integer conoceDerechos;
    @Column(name = "ENTORNO_ANIMA_INDEPENDENCIA")
    private Integer entornoAnimaIndependencia;

    @Lob
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    public DimControlEntorno() { }

    public DimControlEntorno(Integer idDimControl) { this.idDimControl = idDimControl; }

    public Integer getIdDimControl() { return idDimControl; }
    public void setIdDimControl(Integer idDimControl) { this.idDimControl = idDimControl; }

    public Caracterizacion getCaracterizacionId() { return caracterizacionId; }
    public void setCaracterizacionId(Caracterizacion caracterizacionId) { this.caracterizacionId = caracterizacionId; }

    public Integer getReconoceIntereses() { return reconoceIntereses; }
    public void setReconoceIntereses(Integer reconoceIntereses) { this.reconoceIntereses = reconoceIntereses; }

    public Integer getTieneMetas() { return tieneMetas; }
    public void setTieneMetas(Integer tieneMetas) { this.tieneMetas = tieneMetas; }

    public Integer getPlanesReflejanDeseos() { return planesReflejanDeseos; }
    public void setPlanesReflejanDeseos(Integer planesReflejanDeseos) { this.planesReflejanDeseos = planesReflejanDeseos; }

    public Integer getIdentificaFortalezas() { return identificaFortalezas; }
    public void setIdentificaFortalezas(Integer identificaFortalezas) { this.identificaFortalezas = identificaFortalezas; }

    public Integer getSeguridadDecisiones() { return seguridadDecisiones; }
    public void setSeguridadDecisiones(Integer seguridadDecisiones) { this.seguridadDecisiones = seguridadDecisiones; }

    public Integer getDecideLugares() { return decideLugares; }
    public void setDecideLugares(Integer decideLugares) { this.decideLugares = decideLugares; }

    public Integer getRequierePocoApoyo() { return requierePocoApoyo; }
    public void setRequierePocoApoyo(Integer requierePocoApoyo) { this.requierePocoApoyo = requierePocoApoyo; }

    public Integer getConscienteConsecuencias() { return conscienteConsecuencias; }
    public void setConscienteConsecuencias(Integer conscienteConsecuencias) { this.conscienteConsecuencias = conscienteConsecuencias; }

    public Integer getLibertadExpresion() { return libertadExpresion; }
    public void setLibertadExpresion(Integer libertadExpresion) { this.libertadExpresion = libertadExpresion; }

    public Integer getFamiliaRespetaDecisiones() { return familiaRespetaDecisiones; }
    public void setFamiliaRespetaDecisiones(Integer familiaRespetaDecisiones) { this.familiaRespetaDecisiones = familiaRespetaDecisiones; }

    public Integer getConoceDerechos() { return conoceDerechos; }
    public void setConoceDerechos(Integer conoceDerechos) { this.conoceDerechos = conoceDerechos; }

    public Integer getEntornoAnimaIndependencia() { return entornoAnimaIndependencia; }
    public void setEntornoAnimaIndependencia(Integer entornoAnimaIndependencia) { this.entornoAnimaIndependencia = entornoAnimaIndependencia; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
