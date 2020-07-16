/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.entities.Credencial;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.entities.Credencial_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.entities.Empleado;
import com.jd.entities.Rol;
import dao.LoginDao;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class CredencialFacade extends AbstractFacade<Credencial> {

    @PersistenceContext(unitName = "AudioVisualesV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CredencialFacade() {
        super(Credencial.class);
    }

    public boolean isEmpleadoEmpty(Credencial entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Credencial> credencial = cq.from(Credencial.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(credencial, entity), cb.isNotNull(credencial.get(Credencial_.empleado)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empleado findEmpleado(Credencial entity) {
        return this.getMergedEntity(entity).getEmpleado();
    }

    public boolean isIdRolEmpty(Credencial entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Credencial> credencial = cq.from(Credencial.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(credencial, entity), cb.isNotNull(credencial.get(Credencial_.idRol)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Rol findIdRol(Credencial entity) {
        return this.getMergedEntity(entity).getIdRol();
    }

    public Credencial FindByUsername(LoginDao cd) {
        Credencial model = null;
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("AudioVisualesV2PU");
        EntityManager entitymanager
                = this.getEntityManager();
        //emfactory.createEntityManager();
        try {
            //CriteriaBuilder cb = em.getCriteriaBuilder();
            Query q = entitymanager.createNamedQuery("Credencial.findByUsername");
            q.setParameter("username", cd.getUsername());
            model = (Credencial) q.getSingleResult();

        } catch (Exception e) {
            throw e;
        }

        return model;
    }

    public Credencial CheckLogin(LoginDao cd) {
        Credencial model = this.FindByUsername(cd);
        if (model != null) {
            if (!cd.getPassword().equals(model.getPassword())) {
                model = null;
            }
        }
        return model;
    }
}
