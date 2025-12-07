package com.sena.sitea.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.sena.sitea.services.ValoracionInstrumentoService;
import java.util.Date;

@Named("testIMBean")
@ViewScoped
public class TestIMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public static class Pregunta implements Serializable {
        private String tipoInteligencia;
        private String enunciado;
        private Integer puntajeSeleccionado;

        public Pregunta() {}
        public Pregunta(String tipo, String enunciado) { this.tipoInteligencia = tipo; this.enunciado = enunciado; this.puntajeSeleccionado =  null; }
        public String getTipoInteligencia() { return tipoInteligencia; }
        public void setTipoInteligencia(String tipoInteligencia) { this.tipoInteligencia = tipoInteligencia; }
        public String getEnunciado() { return enunciado; }
        public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
        public Integer getPuntajeSeleccionado() { return puntajeSeleccionado; }
        public void setPuntajeSeleccionado(Integer puntajeSeleccionado) { this.puntajeSeleccionado = puntajeSeleccionado; }
    }

    private List<Pregunta> listaPreguntas;
    private Map<String,Integer> resultadosMap;
    private boolean mostrarResultados;

    private Integer caracterizacionId;

    @EJB
    private ValoracionInstrumentoService valoracionInstrumentoService;

    @PostConstruct
    public void init() {
        listaPreguntas = new ArrayList<>();
        resultadosMap = new HashMap<>();
        mostrarResultados = false;

        // preguntas de ejemplo por tipo de inteligencia
        listaPreguntas.add(new Pregunta("Lógico-matemática","Resuelve problemas con razonamiento lógico."));
        listaPreguntas.add(new Pregunta("Lógico-matemática","Disfruta de actividades que implican números y patrones."));
        listaPreguntas.add(new Pregunta("Lingüística","Expresa sus ideas con claridad y vocabulario variado."));
        listaPreguntas.add(new Pregunta("Lingüística","Disfruta leer y escribir textos largos."));
        listaPreguntas.add(new Pregunta("Corporal-kinestésica","Utiliza el cuerpo para resolver problemas o expresarse."));
        listaPreguntas.add(new Pregunta("Corporal-kinestésica","Demuestra buena coordinación motora."));
        listaPreguntas.add(new Pregunta("Interpersonal","Se relaciona bien con otros y percibe emociones ajenas."));
        listaPreguntas.add(new Pregunta("Intrapersonal","Muestra autoconocimiento y regula sus emociones."));
        listaPreguntas.add(new Pregunta("Musical","Se interesa por ritmos, melodías o tocar instrumentos."));
    }

    public List<Pregunta> getListaPreguntas() { return listaPreguntas; }

    public Map<String,Integer> getResultadosMap() { return resultadosMap; }

    public boolean isMostrarResultados() { return mostrarResultados; }

    // actionListener expects ActionEvent
    public void calcularResultadosPreliminares(ActionEvent evt) {
        resultadosMap.clear();
        boolean todasRespondidas = true;
        for (Pregunta p : listaPreguntas) {
            Integer v = p.getPuntajeSeleccionado();
            if (v == null) { todasRespondidas = false; }
            String key = p.getTipoInteligencia();
            resultadosMap.put(key, resultadosMap.getOrDefault(key, 0) + (v == null ? 0 : v));
        }
        if (!todasRespondidas) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Faltan respuestas", "Por favor responda todas las preguntas antes de calcular."));
            mostrarResultados = false;
            return;
        }
        mostrarResultados = true;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultados calculados", "Revise los resultados preliminares."));
    }

    public String guardarValoracionFinal() {
        // Preparar respuestas
        Map<String,Integer> respuestas = new HashMap<>();
        int sum = 0; int count = 0;
        int idx = 1;
        for (Pregunta p : listaPreguntas) {
            Integer v = p.getPuntajeSeleccionado();
            String key = "IM_q" + idx;
            respuestas.put(key, (v == null ? 0 : v));
            if (v != null && v > 0) { sum += v; count++; }
            idx++;
        }

        if (count == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sin respuestas", "No hay respuestas para guardar."));
            return null;
        }

        int maxPoints = count * 5;
        int porcentaje = (maxPoints == 0) ? 0 : (int) Math.round(((double) sum / (double) maxPoints) * 100);

        // Intentar obtener caracterizacionId desde el controlador de sesión
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            com.sena.sitea.controller.Caracterizacioncontroller ctrl = fc.getApplication().evaluateExpressionGet(fc, "#{caracterizacioncontroller}", com.sena.sitea.controller.Caracterizacioncontroller.class);
            if (ctrl != null && ctrl.getCar() != null && ctrl.getCar().getIdCaracterizacion() != null) {
                this.caracterizacionId = ctrl.getCar().getIdCaracterizacion();
            }
        } catch (Exception ex) {
            // ignore
        }

        String estudianteIdent = null;
        if (this.caracterizacionId != null) {
            // obtener número de documento del estudiante desde el controlador si está disponible
            try {
                FacesContext fc = FacesContext.getCurrentInstance();
                com.sena.sitea.controller.Caracterizacioncontroller ctrl = fc.getApplication().evaluateExpressionGet(fc, "#{caracterizacioncontroller}", com.sena.sitea.controller.Caracterizacioncontroller.class);
                if (ctrl != null && ctrl.getCar() != null && ctrl.getCar().getEstudianteIdEstudiante() != null) {
                    estudianteIdent = ctrl.getCar().getEstudianteIdEstudiante().getNumeroDocumentoEstudiante();
                }
            } catch (Exception ex) {}
        }

        try {
            valoracionInstrumentoService.saveInstrument(this.caracterizacionId, "IM", estudianteIdent, new Date(), porcentaje, null, null, respuestas);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado", "La valoración de Inteligencias Múltiples se guardó correctamente."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", ex.getMessage()));
        }
        return null;
    }
}
