/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.facade;

import com.jd.models.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jd.models.Usuario_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jd.models.Rentadevolucion;
import com.jd.models.Tipopersona;
import com.jd.models.Tipousuario;
import java.util.Collection;

/**
 *
 * @author Jesus Dicent
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "AudioVisualesISO715PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public boolean isRentadevolucionCollectionEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.rentadevolucionCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Rentadevolucion> findRentadevolucionCollection(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        Collection<Rentadevolucion> rentadevolucionCollection = mergedEntity.getRentadevolucionCollection();
        rentadevolucionCollection.size();
        return rentadevolucionCollection;
    }

    public boolean isIdTipoPersonaEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.idTipoPersona)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tipopersona findIdTipoPersona(Usuario entity) {
        return this.getMergedEntity(entity).getIdTipoPersona();
    }

    public boolean isIdTipoUsuarioEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.idTipoUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tipousuario findIdTipoUsuario(Usuario entity) {
        return this.getMergedEntity(entity).getIdTipoUsuario();
    }
    
}
