/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.models.Modelos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.models.Modelos_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.models.Equipo;
import com.jd.models.Marcas;
import java.util.Collection;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class ModelosFacade extends AbstractFacade<Modelos> {

    @PersistenceContext(unitName = "AudioVisualesISO715PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModelosFacade() {
        super(Modelos.class);
    }

    public boolean isEquipoCollectionEmpty(Modelos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Modelos> modelos = cq.from(Modelos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(modelos, entity), cb.isNotEmpty(modelos.get(Modelos_.equipoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Equipo> findEquipoCollection(Modelos entity) {
        Modelos mergedEntity = this.getMergedEntity(entity);
        Collection<Equipo> equipoCollection = mergedEntity.getEquipoCollection();
        equipoCollection.size();
        return equipoCollection;
    }

    public boolean isIdMarcaEmpty(Modelos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Modelos> modelos = cq.from(Modelos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(modelos, entity), cb.isNotNull(modelos.get(Modelos_.idMarca)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Marcas findIdMarca(Modelos entity) {
        return this.getMergedEntity(entity).getIdMarca();
    }
    
}
