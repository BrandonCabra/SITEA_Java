/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.PiarHasEstrategiasEducativas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface PiarHasEstrategiasEducativasFacadeLocal {

    void create(PiarHasEstrategiasEducativas piarHasEstrategiasEducativas);

    void edit(PiarHasEstrategiasEducativas piarHasEstrategiasEducativas);

    void remove(PiarHasEstrategiasEducativas piarHasEstrategiasEducativas);

    PiarHasEstrategiasEducativas find(Object id);

    List<PiarHasEstrategiasEducativas> findAll();

    List<PiarHasEstrategiasEducativas> findRange(int[] range);

    int count();
    
}
