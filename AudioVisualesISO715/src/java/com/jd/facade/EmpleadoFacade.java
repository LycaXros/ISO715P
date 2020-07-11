/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.models.Empleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.models.Empleado_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.models.Rentadevolucion;
import com.jd.models.Credencial;
import java.util.Collection;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "AudioVisualesISO715PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }

    public boolean isRentadevolucionCollectionEmpty(Empleado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empleado> empleado = cq.from(Empleado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empleado, entity), cb.isNotEmpty(empleado.get(Empleado_.rentadevolucionCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Rentadevolucion> findRentadevolucionCollection(Empleado entity) {
        Empleado mergedEntity = this.getMergedEntity(entity);
        Collection<Rentadevolucion> rentadevolucionCollection = mergedEntity.getRentadevolucionCollection();
        rentadevolucionCollection.size();
        return rentadevolucionCollection;
    }

    public boolean isCredencialEmpty(Empleado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empleado> empleado = cq.from(Empleado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empleado, entity), cb.isNotNull(empleado.get(Empleado_.credencial)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Credencial findCredencial(Empleado entity) {
        return this.getMergedEntity(entity).getCredencial();
    }
    
}
