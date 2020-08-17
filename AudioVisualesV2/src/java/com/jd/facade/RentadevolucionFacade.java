/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.entities.Rentadevolucion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.entities.Rentadevolucion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.entities.Empleado;
import com.jd.entities.Equipo;
import com.jd.entities.Usuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Query;
import reportes.ReporteVentaModel;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class RentadevolucionFacade extends AbstractFacade<Rentadevolucion> {

    @PersistenceContext(unitName = "AudioVisualesV2PU")
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

    public Collection<Rentadevolucion> GetResult(ReporteVentaModel model) {
        if(model.getFechaInicio() == null || model.getFechaFin() == null) return null;
        
       // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Query query = em.createNamedQuery("Rentadevolucion.betweenFechaPrestamo");
//        query.setParameter("fechaIn", simpleDateFormat.format(model.getFechaInicio()));
//        query.setParameter("fechaFin", simpleDateFormat.format(model.getFechaFin()));
        query.setParameter("fechaIn", model.getFechaInicio());
        query.setParameter("fechaFin", model.getFechaFin());
        return  (List<Rentadevolucion>) query.getResultList();
    }

    public Collection<Rentadevolucion> getRentados() {
        List<Rentadevolucion> rentas = new ArrayList<Rentadevolucion>();
         try {
            //CriteriaBuilder cb = em.getCriteriaBuilder();
            Query q = getEntityManager().createNamedQuery("Rentadevolucion.findRentados");
            //q.setParameter("renta", "S");
            rentas =  q.getResultList();

        } catch (Exception e) {
            throw e;
        }
         return rentas;
    }

}
    