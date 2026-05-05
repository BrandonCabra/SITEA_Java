package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "valoracion_instrumento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValoracionInstrumento.findAll", query = "SELECT v FROM ValoracionInstrumento v")
})
public class ValoracionInstrumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VALORACION_INSTRUMENTO")
    private Integer idValoracion;

    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private Caracterizacion caracterizacionId;

    @Column(name = "DIMENSION")
    private String dimension;

    @Column(name = "ESTUDIANTE_IDENTIFICACION")
    private String estudianteIdentificacion;

    @Column(name = "FECHA_VALORACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaValoracion;

    @Column(name = "PUNTUACION_TOTAL")
    private Integer puntuacionTotal;

    @Lob
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Lob
    @Column(name = "RECOMENDACIONES")
    private String recomendaciones;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "valoracionInstrumento", fetch = FetchType.LAZY)
    private List<ValoracionRespuesta> respuestas;

    public ValoracionInstrumento() {}

    // getters y setters
    public Integer getIdValoracion() { return idValoracion; }
    public void setIdValoracion(Integer idValoracion) { this.idValoracion = idValoracion; }

    public Caracterizacion getCaracterizacionId() { return caracterizacionId; }
    public void setCaracterizacionId(Caracterizacion caracterizacionId) { this.caracterizacionId = caracterizacionId; }

    public String getDimension() { return dimension; }
    public void setDimension(String dimension) { this.dimension = dimension; }

    public String getEstudianteIdentificacion() { return estudianteIdentificacion; }
    public void setEstudianteIdentificacion(String estudianteIdentificacion) { this.estudianteIdentificacion = estudianteIdentificacion; }

    public Date getFechaValoracion() { return fechaValoracion; }
    public void setFechaValoracion(Date fechaValoracion) { this.fechaValoracion = fechaValoracion; }

    public Integer getPuntuacionTotal() { return puntuacionTotal; }
    public void setPuntuacionTotal(Integer puntuacionTotal) { this.puntuacionTotal = puntuacionTotal; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(String recomendaciones) { this.recomendaciones = recomendaciones; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public List<ValoracionRespuesta> getRespuestas() { return respuestas; }
    public void setRespuestas(List<ValoracionRespuesta> respuestas) { this.respuestas = respuestas; }
}
