/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.EstrategiasEducativas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bjcab
 */
@Local
public interface EstrategiasEducativasFacadeLocal {

    void create(EstrategiasEducativas estrategiasEducativas);

    void edit(EstrategiasEducativas estrategiasEducativas);

    void remove(EstrategiasEducativas estrategiasEducativas);

    EstrategiasEducativas find(Object id);

    List<EstrategiasEducativas> findAll();

    List<EstrategiasEducativas> findRange(int[] range);

    int count();
    
}
