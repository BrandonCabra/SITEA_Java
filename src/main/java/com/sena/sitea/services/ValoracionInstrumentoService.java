package com.sena.sitea.services;

import com.sena.sitea.entities.ValoracionInstrumento;
import com.sena.sitea.entities.ValoracionRespuesta;
import com.sena.sitea.entities.Caracterizacion;
import com.sena.sitea.services.CaracterizacionFacadeLocal;
import java.util.Date;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ValoracionInstrumentoService {

    @EJB
    private ValoracionInstrumentoFacadeLocal valoracionInstrumentoFacade;

    @EJB
    private ValoracionRespuestaFacadeLocal valoracionRespuestaFacade;

    @EJB
    private CaracterizacionFacadeLocal caracterizacionFacade;

    public ValoracionInstrumentoService() {}

    /**
     * Guarda una valoración de instrumento y sus respuestas.
     * @param caracterizacionId puede ser null si no se dispone
     * @param dimension nombre o código de la dimensión (ej. D1)
     * @param estudianteIdentificacion identificación del estudiante (opcional)
     * @param fechaValoracion fecha de la valoración
     * @param puntuacionTotal puntuación calculada (porcentaje)
     * @param observaciones observaciones generales
     * @param recomendaciones recomendaciones generales
     * @param respuestas mapa preguntaKey -> valor (1..5)
     */
    public void saveInstrument(Integer caracterizacionId, String dimension, String estudianteIdentificacion, Date fechaValoracion, Integer puntuacionTotal, String observaciones, String recomendaciones, Map<String,Integer> respuestas) {
        ValoracionInstrumento v = new ValoracionInstrumento();
        // Si proporcionan un id de caracterización, intentar enlazarla y validar TEA
        if (caracterizacionId != null) {
            Caracterizacion c = caracterizacionFacade.find(caracterizacionId);
            if (c == null) {
                throw new IllegalArgumentException("Caracterización no encontrada: " + caracterizacionId);
            }
            // Verificar que el estudiante asociado tenga diagnóstico TEA o diagnóstico certificado
            if (c.getEstudianteIdEstudiante() == null) {
                throw new IllegalArgumentException("La caracterización no está vinculada a un estudiante.");
            }
            Boolean diagCert = c.getEstudianteIdEstudiante().getDiagnosticoCertificado();
            String tipoTea = c.getEstudianteIdEstudiante().getTipoTea();
            if ((diagCert == null || !diagCert) && (tipoTea == null || tipoTea.trim().isEmpty())) {
                throw new IllegalArgumentException("La caracterización no está vinculada a un estudiante con diagnóstico TEA.");
            }
            v.setCaracterizacionId(c);
        }
        v.setDimension(dimension);
        v.setEstudianteIdentificacion(estudianteIdentificacion);
        v.setFechaValoracion(fechaValoracion);
        v.setPuntuacionTotal(puntuacionTotal);
        v.setObservaciones(observaciones);
        v.setRecomendaciones(recomendaciones);
        v.setCreatedAt(new Date());

        valoracionInstrumentoFacade.create(v);

        if (respuestas != null) {
            for (Map.Entry<String,Integer> e : respuestas.entrySet()) {
                // solo persistir las respuestas que pertenezcan a la dimensión indicada
                if (e.getKey().startsWith(dimension + "_q")) {
                    ValoracionRespuesta r = new ValoracionRespuesta();
                    r.setValoracionInstrumento(v);
                    r.setPreguntaKey(e.getKey());
                    r.setValor(e.getValue());
                    r.setCreatedAt(new Date());
                    valoracionRespuestaFacade.create(r);
                }
            }
        }
    }
}
