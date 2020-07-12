/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.entities.Equipo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.entities.Equipo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.entities.Rentadevolucion;
import com.jd.entities.Modelos;
import com.jd.entities.Tecconexion;
import com.jd.entities.Tipoequipo;
import java.util.Collection;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class EquipoFacade extends AbstractFacade<Equipo> {

    @PersistenceContext(unitName = "AudioVisualesV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }

    public boolean isRentadevolucionCollectionEmpty(Equipo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Equipo> equipo = cq.from(Equipo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(equipo, entity), cb.isNotEmpty(equipo.get(Equipo_.rentadevolucionCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Rentadevolucion> findRentadevolucionCollection(Equipo entity) {
        Equipo mergedEntity = this.getMergedEntity(entity);
        Collection<Rentadevolucion> rentadevolucionCollection = mergedEntity.getRentadevolucionCollection();
        rentadevolucionCollection.size();
        return rentadevolucionCollection;
    }

    public boolean isIdModeloEmpty(Equipo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Equipo> equipo = cq.from(Equipo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(equipo, entity), cb.isNotNull(equipo.get(Equipo_.idModelo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Modelos findIdModelo(Equipo entity) {
        return this.getMergedEntity(entity).getIdModelo();
    }

    public boolean isIdTecConexionEmpty(Equipo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Equipo> equipo = cq.from(Equipo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(equipo, entity), cb.isNotNull(equipo.get(Equipo_.idTecConexion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tecconexion findIdTecConexion(Equipo entity) {
        return this.getMergedEntity(entity).getIdTecConexion();
    }

    public boolean isIdTipoEquipoEmpty(Equipo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Equipo> equipo = cq.from(Equipo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(equipo, entity), cb.isNotNull(equipo.get(Equipo_.idTipoEquipo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tipoequipo findIdTipoEquipo(Equipo entity) {
        return this.getMergedEntity(entity).getIdTipoEquipo();
    }
    
}
