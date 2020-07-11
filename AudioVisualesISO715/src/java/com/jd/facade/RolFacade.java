/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.models.Rol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.models.Rol_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.models.Credencial;
import java.util.Collection;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "AudioVisualesISO715PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    public boolean isCredencialCollectionEmpty(Rol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rol> rol = cq.from(Rol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rol, entity), cb.isNotEmpty(rol.get(Rol_.credencialCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Credencial> findCredencialCollection(Rol entity) {
        Rol mergedEntity = this.getMergedEntity(entity);
        Collection<Credencial> credencialCollection = mergedEntity.getCredencialCollection();
        credencialCollection.size();
        return credencialCollection;
    }
    
}
