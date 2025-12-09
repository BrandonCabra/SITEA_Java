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
@Table(name = "dim_habilidades_intelectuales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DimHabilidadesIntelectuales.findAll", query = "SELECT d FROM DimHabilidadesIntelectuales d"),
    @NamedQuery(name = "DimHabilidadesIntelectuales.findByCaracterizacion", query = "SELECT d FROM DimHabilidadesIntelectuales d WHERE d.caracterizacionId = :caracterizacionId")
})
public class DimHabilidadesIntelectuales implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIM_INTELECTUAL")
    private Integer idDimIntelectual;

    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacionId;

    @Lob
    @Column(name = "ATENCION")
    private String atencion;

    @Lob
    @Column(name = "MEMORIA")
    private String memoria;

    @Lob
    @Column(name = "SENSOPERCEPCION")
    private String sensopercepcion;

    @Lob
    @Column(name = "RAZONAMIENTO")
    private String razonamiento;

    @Lob
    @Column(name = "MOTIVACION")
    private String motivacion;

    @Lob
    @Column(name = "LECTURA")
    private String lectura;

    @Lob
    @Column(name = "ESCRITURA")
    private String escritura;

    @Lob
    @Column(name = "MATEMATICAS")
    private String matematicas;

    @Column(name = "ESTILO_APRENDIZAJE_DOMINANTE")
    private String estiloAprendizajeDominante;

    @Column(name = "INTELIGENCIA_MULTIPLE_DOMINANTE")
    private String inteligenciaMultipleDominante;

    @Lob
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    public DimHabilidadesIntelectuales() { }

    public DimHabilidadesIntelectuales(Integer idDimIntelectual) { this.idDimIntelectual = idDimIntelectual; }

    public Integer getIdDimIntelectual() { return idDimIntelectual; }
    public void setIdDimIntelectual(Integer idDimIntelectual) { this.idDimIntelectual = idDimIntelectual; }

    public Caracterizacion getCaracterizacionId() { return caracterizacionId; }
    public void setCaracterizacionId(Caracterizacion caracterizacionId) { this.caracterizacionId = caracterizacionId; }

    public String getAtencion() { return atencion; }
    public void setAtencion(String atencion) { this.atencion = atencion; }

    public String getMemoria() { return memoria; }
    public void setMemoria(String memoria) { this.memoria = memoria; }

    public String getSensopercepcion() { return sensopercepcion; }
    public void setSensopercepcion(String sensopercepcion) { this.sensopercepcion = sensopercepcion; }

    public String getRazonamiento() { return razonamiento; }
    public void setRazonamiento(String razonamiento) { this.razonamiento = razonamiento; }

    public String getMotivacion() { return motivacion; }
    public void setMotivacion(String motivacion) { this.motivacion = motivacion; }

    public String getLectura() { return lectura; }
    public void setLectura(String lectura) { this.lectura = lectura; }

    public String getEscritura() { return escritura; }
    public void setEscritura(String escritura) { this.escritura = escritura; }

    public String getMatematicas() { return matematicas; }
    public void setMatematicas(String matematicas) { this.matematicas = matematicas; }

    public String getEstiloAprendizajeDominante() { return estiloAprendizajeDominante; }
    public void setEstiloAprendizajeDominante(String estiloAprendizajeDominante) { this.estiloAprendizajeDominante = estiloAprendizajeDominante; }

    public String getInteligenciaMultipleDominante() { return inteligenciaMultipleDominante; }
    public void setInteligenciaMultipleDominante(String inteligenciaMultipleDominante) { this.inteligenciaMultipleDominante = inteligenciaMultipleDominante; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
