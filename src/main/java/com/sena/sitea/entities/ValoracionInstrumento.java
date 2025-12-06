package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

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
