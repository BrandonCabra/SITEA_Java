/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Usuarios;
import com.sena.sitea.security.PasswordUtil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author bjcab
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public Usuarios iniciarSesion(String NUMERO_DOCUMENTO, String PASSWORD) {
        Usuarios NUMERO_DOCUMENTOValidar = new Usuarios();
        try {
        Query query = em.createQuery("SELECT U FROM Usuarios U WHERE U.numeroDocumento=:NUMERO_DOCUMENTO AND U.tipoDocumentoIdTipoDocumento.idTipoDocumento=1");
        query.setParameter("NUMERO_DOCUMENTO", NUMERO_DOCUMENTO);          
        NUMERO_DOCUMENTOValidar = (Usuarios) query.getSingleResult();
        if (PasswordUtil.checkPassword(PASSWORD, NUMERO_DOCUMENTOValidar.getPassword())){
        return NUMERO_DOCUMENTOValidar;   
        }
        
        } catch (Exception e) {
        }
        Usuarios usuarioVacio = new Usuarios();
        return usuarioVacio;
    }
    
}
