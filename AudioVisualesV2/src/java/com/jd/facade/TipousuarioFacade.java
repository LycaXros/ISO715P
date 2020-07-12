/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.entities.Tipousuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.entities.Tipousuario_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.entities.Usuario;
import java.util.Collection;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class TipousuarioFacade extends AbstractFacade<Tipousuario> {

    @PersistenceContext(unitName = "AudioVisualesV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipousuarioFacade() {
        super(Tipousuario.class);
    }

    public boolean isUsuarioCollectionEmpty(Tipousuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tipousuario> tipousuario = cq.from(Tipousuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipousuario, entity), cb.isNotEmpty(tipousuario.get(Tipousuario_.usuarioCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Usuario> findUsuarioCollection(Tipousuario entity) {
        Tipousuario mergedEntity = this.getMergedEntity(entity);
        Collection<Usuario> usuarioCollection = mergedEntity.getUsuarioCollection();
        usuarioCollection.size();
        return usuarioCollection;
    }
    
}
