package com.mycompany.webserviceconsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para mapear datos del dataset x7m4-b6ys de datos.gov.co
 * Columnas basadas en la estructura real del dataset
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrastornoDto {
    
    // Basado en las columnas reales del dataset x7m4-b6ys
    @JsonProperty("institucion_sede_g_nero_tipo")
    private String institucionTipo;
    
    @JsonProperty("trast_especificos_de")
    private String trastEspecificosDe;
    
    @JsonProperty("trast_especificos_de_1")
    private String trastEspecificosDe1;
    
    @JsonProperty("total")
    private String total;
    
    @JsonProperty("trast_por_deficit_de_atencion")
    private String trastDeficitAtencion;
    
    @JsonProperty("trast_por_deficit_de_atencion_1")
    private String trastDeficitAtencion1;
    
    @JsonProperty("total_1")
    private String total1;
    
    @JsonProperty("total_general")
    private String totalGeneral;
    
    // Constructor vacío requerido por Jackson
    public TrastornoDto() {}
    
    // Getters y setters
    public String getInstitucionTipo() { return institucionTipo; }
    public void setInstitucionTipo(String institucionTipo) { this.institucionTipo = institucionTipo; }
    
    public String getTrastEspecificosDe() { return trastEspecificosDe; }
    public void setTrastEspecificosDe(String trastEspecificosDe) { this.trastEspecificosDe = trastEspecificosDe; }
    
    public String getTrastEspecificosDe1() { return trastEspecificosDe1; }
    public void setTrastEspecificosDe1(String trastEspecificosDe1) { this.trastEspecificosDe1 = trastEspecificosDe1; }
    
    public String getTotal() { return total; }
    public void setTotal(String total) { this.total = total; }
    
    public String getTrastDeficitAtencion() { return trastDeficitAtencion; }
    public void setTrastDeficitAtencion(String trastDeficitAtencion) { this.trastDeficitAtencion = trastDeficitAtencion; }
    
    public String getTrastDeficitAtencion1() { return trastDeficitAtencion1; }
    public void setTrastDeficitAtencion1(String trastDeficitAtencion1) { this.trastDeficitAtencion1 = trastDeficitAtencion1; }
    
    public String getTotal1() { return total1; }
    public void setTotal1(String total1) { this.total1 = total1; }
    
    public String getTotalGeneral() { return totalGeneral; }
    public void setTotalGeneral(String totalGeneral) { this.totalGeneral = totalGeneral; }
    
    @Override
    public String toString() {
        return "TrastornoDto{institucion='" + institucionTipo + "', trastEsp='" + trastEspecificosDe + "'}";
    }
}
