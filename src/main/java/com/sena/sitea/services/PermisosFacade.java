/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.services;

import com.sena.sitea.entities.Permisos;
import com.sena.sitea.entities.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bjcab
 */
@Stateless
public class PermisosFacade extends AbstractFacade<Permisos> implements PermisosFacadeLocal {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisosFacade() {
        super(Permisos.class);
    }

    @Override
    public List<Permisos> PermisosByUsuario(Usuarios usuario) {
        try {
            return em.createQuery("SELECT p FROM Usuarios u "
                    + "JOIN u.usuarioRolList ur "
                    + "JOIN ur.rolIdRol r "
                    + "JOIN r.rolPermisoList rp "
                    + "JOIN rp.permisoIdPermiso p "
                    + "WHERE u.numeroDocumento=:numeroDocumento", Permisos.class).setParameter("numeroDocumento", 
                            usuario.getNumeroDocumento()).getResultList();
            
        } catch (Exception e) {
        }
        return null;
    }
    
}
