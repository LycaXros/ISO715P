/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.entities.Tecconexion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.entities.Tecconexion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.entities.Equipo;
import java.util.Collection;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class TecconexionFacade extends AbstractFacade<Tecconexion> {

    @PersistenceContext(unitName = "AudioVisualesV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TecconexionFacade() {
        super(Tecconexion.class);
    }

    public boolean isEquipoCollectionEmpty(Tecconexion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tecconexion> tecconexion = cq.from(Tecconexion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tecconexion, entity), cb.isNotEmpty(tecconexion.get(Tecconexion_.equipoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Equipo> findEquipoCollection(Tecconexion entity) {
        Tecconexion mergedEntity = this.getMergedEntity(entity);
        Collection<Equipo> equipoCollection = mergedEntity.getEquipoCollection();
        equipoCollection.size();
        return equipoCollection;
    }
    
}
