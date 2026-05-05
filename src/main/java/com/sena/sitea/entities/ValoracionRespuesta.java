package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "valoracion_respuesta")
@XmlRootElement
public class ValoracionRespuesta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RESPUESTA")
    private Integer idRespuesta;

    @JoinColumn(name = "ID_VALORACION_INSTRUMENTO", referencedColumnName = "ID_VALORACION_INSTRUMENTO")
    @ManyToOne(fetch = FetchType.LAZY)
    private ValoracionInstrumento valoracionInstrumento;

    @Column(name = "PREGUNTA_KEY")
    private String preguntaKey;

    @Column(name = "VALOR")
    private Integer valor;

    @Lob
    @Column(name = "COMENTARIO")
    private String comentario;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public ValoracionRespuesta() {}

    // getters y setters
    public Integer getIdRespuesta() { return idRespuesta; }
    public void setIdRespuesta(Integer idRespuesta) { this.idRespuesta = idRespuesta; }

    public ValoracionInstrumento getValoracionInstrumento() { return valoracionInstrumento; }
    public void setValoracionInstrumento(ValoracionInstrumento valoracionInstrumento) { this.valoracionInstrumento = valoracionInstrumento; }

    public String getPreguntaKey() { return preguntaKey; }
    public void setPreguntaKey(String preguntaKey) { this.preguntaKey = preguntaKey; }

    public Integer getValor() { return valor; }
    public void setValor(Integer valor) { this.valor = valor; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
