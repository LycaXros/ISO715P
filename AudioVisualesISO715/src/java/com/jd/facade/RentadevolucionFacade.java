/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.models.Rentadevolucion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.models.Rentadevolucion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.models.Empleado;
import com.jd.models.Equipo;
import com.jd.models.Usuario;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class RentadevolucionFacade extends AbstractFacade<Rentadevolucion> {

    @PersistenceContext(unitName = "AudioVisualesISO715PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RentadevolucionFacade() {
        super(Rentadevolucion.class);
    }

    public boolean isIdEmpleadoEmpty(Rentadevolucion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rentadevolucion> rentadevolucion = cq.from(Rentadevolucion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rentadevolucion, entity), cb.isNotNull(rentadevolucion.get(Rentadevolucion_.idEmpleado)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empleado findIdEmpleado(Rentadevolucion entity) {
        return this.getMergedEntity(entity).getIdEmpleado();
    }

    public boolean isIdEquipoEmpty(Rentadevolucion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rentadevolucion> rentadevolucion = cq.from(Rentadevolucion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rentadevolucion, entity), cb.isNotNull(rentadevolucion.get(Rentadevolucion_.idEquipo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Equipo findIdEquipo(Rentadevolucion entity) {
        return this.getMergedEntity(entity).getIdEquipo();
    }

    public boolean isIdUsuarioEmpty(Rentadevolucion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rentadevolucion> rentadevolucion = cq.from(Rentadevolucion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rentadevolucion, entity), cb.isNotNull(rentadevolucion.get(Rentadevolucion_.idUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findIdUsuario(Rentadevolucion entity) {
        return this.getMergedEntity(entity).getIdUsuario();
    }
    
}
