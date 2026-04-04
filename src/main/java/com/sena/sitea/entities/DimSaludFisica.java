package com.sena.sitea.entities;

import java.io.Serializable;
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
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "dim_salud_fisica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DimSaludFisica.findAll", query = "SELECT d FROM DimSaludFisica d"),
    @NamedQuery(name = "DimSaludFisica.findByCaracterizacion", query = "SELECT d FROM DimSaludFisica d WHERE d.caracterizacionId = :caracterizacionId")
})
public class DimSaludFisica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIM_SALUD")
    private Integer idDimSalud;

    @JoinColumn(name = "CARACTERIZACION_ID", referencedColumnName = "ID_CARACTERIZACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caracterizacion caracterizacionId;

    @Lob
    @Column(name = "ANTECEDENTES_MEDICOS")
    private String antecedentesMedicos;

    @Lob
    @Column(name = "MEDICACION_ACTUAL")
    private String medicacionActual;

    @Lob
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "AFILIADO_SISTEMA_SALUD")
    private Boolean afiliadoSistemaSalud;

    @Column(name = "NOMBRE_EPS_ARS")
    private String nombreEpsArs;

    @Column(name = "REGIMEN_SALUD")
    private String regimenSalud;

    @Column(name = "LUGAR_ATENCION_URGENCIAS")
    private String lugarAtencionUrgencias;

    @Column(name = "TIENE_DIAGNOSTICO_MEDICO")
    private Boolean tieneDiagnosticoMedico;

    @Lob
    @Column(name = "DETALLE_DIAGNOSTICO")
    private String detalleDiagnostico;

    @Column(name = "TOMA_MEDICAMENTOS")
    private Boolean tomaMedicamentos;

    @Lob
    @Column(name = "DETALLE_MEDICAMENTOS_DOSIS")
    private String detalleMedicamentosDosis;

    @Column(name = "TIENE_APOYOS_TERAPEUTICOS")
    private Boolean tieneApoyosTerapeuticos;

    @Lob
    @Column(name = "DETALLE_APOYOS_EXTERNOS")
    private String detalleApoyosExternos;

    @Lob
    @Column(name = "ALERGIAS_CONOCIDAS")
    private String alergiasConocidas;

    public DimSaludFisica() { }

    public DimSaludFisica(Integer idDimSalud) { this.idDimSalud = idDimSalud; }

    public Integer getIdDimSalud() { return idDimSalud; }
    public void setIdDimSalud(Integer idDimSalud) { this.idDimSalud = idDimSalud; }

    public Caracterizacion getCaracterizacionId() { return caracterizacionId; }
    public void setCaracterizacionId(Caracterizacion caracterizacionId) { this.caracterizacionId = caracterizacionId; }

    public String getAntecedentesMedicos() { return antecedentesMedicos; }
    public void setAntecedentesMedicos(String antecedentesMedicos) { this.antecedentesMedicos = antecedentesMedicos; }

    public String getMedicacionActual() { return medicacionActual; }
    public void setMedicacionActual(String medicacionActual) { this.medicacionActual = medicacionActual; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Boolean getAfiliadoSistemaSalud() { return afiliadoSistemaSalud; }
    public void setAfiliadoSistemaSalud(Boolean afiliadoSistemaSalud) { this.afiliadoSistemaSalud = afiliadoSistemaSalud; }

    public String getNombreEpsArs() { return nombreEpsArs; }
    public void setNombreEpsArs(String nombreEpsArs) { this.nombreEpsArs = nombreEpsArs; }

    public String getRegimenSalud() { return regimenSalud; }
    public void setRegimenSalud(String regimenSalud) { this.regimenSalud = regimenSalud; }

    public String getLugarAtencionUrgencias() { return lugarAtencionUrgencias; }
    public void setLugarAtencionUrgencias(String lugarAtencionUrgencias) { this.lugarAtencionUrgencias = lugarAtencionUrgencias; }

    public Boolean getTieneDiagnosticoMedico() { return tieneDiagnosticoMedico; }
    public void setTieneDiagnosticoMedico(Boolean tieneDiagnosticoMedico) { this.tieneDiagnosticoMedico = tieneDiagnosticoMedico; }

    public String getDetalleDiagnostico() { return detalleDiagnostico; }
    public void setDetalleDiagnostico(String detalleDiagnostico) { this.detalleDiagnostico = detalleDiagnostico; }

    public Boolean getTomaMedicamentos() { return tomaMedicamentos; }
    public void setTomaMedicamentos(Boolean tomaMedicamentos) { this.tomaMedicamentos = tomaMedicamentos; }

    public String getDetalleMedicamentosDosis() { return detalleMedicamentosDosis; }
    public void setDetalleMedicamentosDosis(String detalleMedicamentosDosis) { this.detalleMedicamentosDosis = detalleMedicamentosDosis; }

    public Boolean getTieneApoyosTerapeuticos() { return tieneApoyosTerapeuticos; }
    public void setTieneApoyosTerapeuticos(Boolean tieneApoyosTerapeuticos) { this.tieneApoyosTerapeuticos = tieneApoyosTerapeuticos; }

    public String getDetalleApoyosExternos() { return detalleApoyosExternos; }
    public void setDetalleApoyosExternos(String detalleApoyosExternos) { this.detalleApoyosExternos = detalleApoyosExternos; }

    public String getAlergiasConocidas() { return alergiasConocidas; }
    public void setAlergiasConocidas(String alergiasConocidas) { this.alergiasConocidas = alergiasConocidas; }
}
