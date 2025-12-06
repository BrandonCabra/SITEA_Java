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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

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
