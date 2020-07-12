/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jesus Dicent
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipopersona.findAll", query = "SELECT t FROM Tipopersona t")
    , @NamedQuery(name = "Tipopersona.findByIdTipoPesona", query = "SELECT t FROM Tipopersona t WHERE t.idTipoPesona = :idTipoPesona")
    , @NamedQuery(name = "Tipopersona.findByDescripcion", query = "SELECT t FROM Tipopersona t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Tipopersona.findByEstado", query = "SELECT t FROM Tipopersona t WHERE t.estado = :estado")})
public class Tipopersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idTipoPesona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    private boolean estado;
    @OneToMany(mappedBy = "idTipoPersona")
    private Collection<Usuario> usuarioCollection;

    public Tipopersona() {
    }

    public Tipopersona(Integer idTipoPesona) {
        this.idTipoPesona = idTipoPesona;
    }

    public Tipopersona(Integer idTipoPesona, String descripcion, boolean estado) {
        this.idTipoPesona = idTipoPesona;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getIdTipoPesona() {
        return idTipoPesona;
    }

    public void setIdTipoPesona(Integer idTipoPesona) {
        this.idTipoPesona = idTipoPesona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPesona != null ? idTipoPesona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipopersona)) {
            return false;
        }
        Tipopersona other = (Tipopersona) object;
        if ((this.idTipoPesona == null && other.idTipoPesona != null) || (this.idTipoPesona != null && !this.idTipoPesona.equals(other.idTipoPesona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jd.entities.Tipopersona[ idTipoPesona=" + idTipoPesona + " ]";
    }
    
}
