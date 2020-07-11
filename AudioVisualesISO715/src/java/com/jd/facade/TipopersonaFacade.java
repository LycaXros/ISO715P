/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.models.Tipopersona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.models.Tipopersona_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.models.Usuario;
import java.util.Collection;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class TipopersonaFacade extends AbstractFacade<Tipopersona> {

    @PersistenceContext(unitName = "AudioVisualesISO715PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipopersonaFacade() {
        super(Tipopersona.class);
    }

    public boolean isUsuarioCollectionEmpty(Tipopersona entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tipopersona> tipopersona = cq.from(Tipopersona.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipopersona, entity), cb.isNotEmpty(tipopersona.get(Tipopersona_.usuarioCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Usuario> findUsuarioCollection(Tipopersona entity) {
        Tipopersona mergedEntity = this.getMergedEntity(entity);
        Collection<Usuario> usuarioCollection = mergedEntity.getUsuarioCollection();
        usuarioCollection.size();
        return usuarioCollection;
    }
    
}
