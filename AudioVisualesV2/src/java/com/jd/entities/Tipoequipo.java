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
    @NamedQuery(name = "Tipoequipo.findAll", query = "SELECT t FROM Tipoequipo t")
    , @NamedQuery(name = "Tipoequipo.findById", query = "SELECT t FROM Tipoequipo t WHERE t.id = :id")
    , @NamedQuery(name = "Tipoequipo.findByDescripcion", query = "SELECT t FROM Tipoequipo t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Tipoequipo.findByEstado", query = "SELECT t FROM Tipoequipo t WHERE t.estado = :estado")})
public class Tipoequipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    private boolean estado;
    @OneToMany(mappedBy = "idTipoEquipo")
    private Collection<Equipo> equipoCollection;

    public Tipoequipo() {
    }

    public Tipoequipo(Integer id) {
        this.id = id;
    }

    public Tipoequipo(Integer id, String descripcion, boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public Collection<Equipo> getEquipoCollection() {
        return equipoCollection;
    }

    public void setEquipoCollection(Collection<Equipo> equipoCollection) {
        this.equipoCollection = equipoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoequipo)) {
            return false;
        }
        Tipoequipo other = (Tipoequipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jd.entities.Tipoequipo[ id=" + id + " ]";
    }
    
}
