package com.sena.sitea.services;

import com.sena.sitea.entities.DimParticipacionSocial;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DimParticipacionSocialFacadeLocal {

    void create(DimParticipacionSocial dimParticipacionSocial);

    void edit(DimParticipacionSocial dimParticipacionSocial);

    void remove(DimParticipacionSocial dimParticipacionSocial);

    DimParticipacionSocial find(Object id);

    java.util.List<DimParticipacionSocial> findAll();

    java.util.List<DimParticipacionSocial> findRange(int[] range);

    int count();

    java.util.List<DimParticipacionSocial> findByCaracterizacion(Integer caracterizacionId);
}
