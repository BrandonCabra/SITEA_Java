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
@Table(name = "dim_pedagogica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DimPedagogica.findAll", query = "SELECT d FROM DimPedagogica d"),
    @NamedQuery(name = "DimPedagogica.findByCaracterizacion", query = "SELECT d FROM DimPedagogica d WHERE d.caracterizacionId = :caracterizacionId")
})
public class DimPedagogica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIM_PEDAGOGICA")
    private Integer idDimPedagogica;

    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacionId;

    @Lob
    @Column(name = "DESCRIPCION_METODOS")
    private String descripcionMetodos;

    @Lob
    @Column(name = "NECESIDADES_APOYO")
    private String necesidadesApoyo;

    @Lob
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    // --- Campos añadidos para coincidir con sitea4(7).sql ---
    @Lob
    @Column(name = "FACTORES_MOTIVACION")
    private String factoresMotivacion;

    @Lob
    @Column(name = "EXPERIENCIAS_DESTACADAS")
    private String experienciasDestacadas;

    @Column(name = "SABERES_CONECTAN")
    private Integer saberesConectan;

    @Column(name = "INTERES_TEMAS")
    private Integer interesTemas;

    @Column(name = "PROFESORES_CONSIDERAN_INTERESES")
    private Integer profesoresConsideranIntereses;

    @Column(name = "OPORTUNIDAD_ELEGIR")
    private Integer oportunidadElegir;

    @Column(name = "ACTIVIDADES_APRENDE_MEJOR")
    private Integer actividadesAprendeMejor;

    @Column(name = "AMBIENTE_AULA_AYUDA")
    private Integer ambienteAulaAyuda;

    @Column(name = "METODOS_ADAPTAN")
    private Integer metodosAdaptan;

    @Column(name = "MOTIVACION_APRENDER")
    private Integer motivacionAprender;

    @Column(name = "METAS_CLARAS")
    private Integer metasClaras;

    @Column(name = "AYUDA_IDENTIFICAR_FORTALEZAS")
    private Integer ayudaIdentificarFortalezas;

    @Column(name = "CAPAZ_ALCANZAR_METAS")
    private Integer capazAlcanzarMetas;

    @Column(name = "CONSCIENTE_HABILIDADES")
    private Integer conscienteHabilidades;

    public DimPedagogica() { 
        // Inicializar valores escala (1-4) por defecto a 0
        this.saberesConectan = 0; this.interesTemas = 0; this.profesoresConsideranIntereses = 0; this.oportunidadElegir = 0;
        this.actividadesAprendeMejor = 0; this.ambienteAulaAyuda = 0; this.metodosAdaptan = 0; this.motivacionAprender = 0;
        this.metasClaras = 0; this.ayudaIdentificarFortalezas = 0; this.capazAlcanzarMetas = 0; this.conscienteHabilidades = 0;
    }

    public DimPedagogica(Integer idDimPedagogica) { this.idDimPedagogica = idDimPedagogica; }

    public Integer getIdDimPedagogica() { return idDimPedagogica; }
    public void setIdDimPedagogica(Integer idDimPedagogica) { this.idDimPedagogica = idDimPedagogica; }

    public Caracterizacion getCaracterizacionId() { return caracterizacionId; }
    public void setCaracterizacionId(Caracterizacion caracterizacionId) { this.caracterizacionId = caracterizacionId; }

    public String getDescripcionMetodos() { return descripcionMetodos; }
    public void setDescripcionMetodos(String descripcionMetodos) { this.descripcionMetodos = descripcionMetodos; }

    public String getNecesidadesApoyo() { return necesidadesApoyo; }
    public void setNecesidadesApoyo(String necesidadesApoyo) { this.necesidadesApoyo = necesidadesApoyo; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getFactoresMotivacion() { return factoresMotivacion; }
    public void setFactoresMotivacion(String factoresMotivacion) { this.factoresMotivacion = factoresMotivacion; }

    public String getExperienciasDestacadas() { return experienciasDestacadas; }
    public void setExperienciasDestacadas(String experienciasDestacadas) { this.experienciasDestacadas = experienciasDestacadas; }

    public Integer getSaberesConectan() { return saberesConectan; }
    public void setSaberesConectan(Integer saberesConectan) { this.saberesConectan = saberesConectan; }

    public Integer getInteresTemas() { return interesTemas; }
    public void setInteresTemas(Integer interesTemas) { this.interesTemas = interesTemas; }

    public Integer getProfesoresConsideranIntereses() { return profesoresConsideranIntereses; }
    public void setProfesoresConsideranIntereses(Integer profesoresConsideranIntereses) { this.profesoresConsideranIntereses = profesoresConsideranIntereses; }

    public Integer getOportunidadElegir() { return oportunidadElegir; }
    public void setOportunidadElegir(Integer oportunidadElegir) { this.oportunidadElegir = oportunidadElegir; }

    public Integer getActividadesAprendeMejor() { return actividadesAprendeMejor; }
    public void setActividadesAprendeMejor(Integer actividadesAprendeMejor) { this.actividadesAprendeMejor = actividadesAprendeMejor; }

    public Integer getAmbienteAulaAyuda() { return ambienteAulaAyuda; }
    public void setAmbienteAulaAyuda(Integer ambienteAulaAyuda) { this.ambienteAulaAyuda = ambienteAulaAyuda; }

    public Integer getMetodosAdaptan() { return metodosAdaptan; }
    public void setMetodosAdaptan(Integer metodosAdaptan) { this.metodosAdaptan = metodosAdaptan; }

    public Integer getMotivacionAprender() { return motivacionAprender; }
    public void setMotivacionAprender(Integer motivacionAprender) { this.motivacionAprender = motivacionAprender; }

    public Integer getMetasClaras() { return metasClaras; }
    public void setMetasClaras(Integer metasClaras) { this.metasClaras = metasClaras; }

    public Integer getAyudaIdentificarFortalezas() { return ayudaIdentificarFortalezas; }
    public void setAyudaIdentificarFortalezas(Integer ayudaIdentificarFortalezas) { this.ayudaIdentificarFortalezas = ayudaIdentificarFortalezas; }

    public Integer getCapazAlcanzarMetas() { return capazAlcanzarMetas; }
    public void setCapazAlcanzarMetas(Integer capazAlcanzarMetas) { this.capazAlcanzarMetas = capazAlcanzarMetas; }

    public Integer getConscienteHabilidades() { return conscienteHabilidades; }
    public void setConscienteHabilidades(Integer conscienteHabilidades) { this.conscienteHabilidades = conscienteHabilidades; }
}
