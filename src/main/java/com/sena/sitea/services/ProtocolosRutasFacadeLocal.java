/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.ProtocolosRutas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface ProtocolosRutasFacadeLocal {

    void create(ProtocolosRutas protocolosRutas);

    void edit(ProtocolosRutas protocolosRutas);

    void remove(ProtocolosRutas protocolosRutas);

    ProtocolosRutas find(Object id);

    List<ProtocolosRutas> findAll();

    List<ProtocolosRutas> findRange(int[] range);

    int count();
    
}
