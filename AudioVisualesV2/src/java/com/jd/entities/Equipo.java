/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e")
    , @NamedQuery(name = "Equipo.findByIdEquipo", query = "SELECT e FROM Equipo e WHERE e.idEquipo = :idEquipo")
    , @NamedQuery(name = "Equipo.findByDescripcion", query = "SELECT e FROM Equipo e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Equipo.findByNoSerial", query = "SELECT e FROM Equipo e WHERE e.noSerial = :noSerial")
    , @NamedQuery(name = "Equipo.findByServiceTag", query = "SELECT e FROM Equipo e WHERE e.serviceTag = :serviceTag")
    , @NamedQuery(name = "Equipo.findByEstado", query = "SELECT e FROM Equipo e WHERE e.estado = :estado")
    , @NamedQuery(name = "Equipo.NoRentados", query = "SELECT e FROM Equipo e WHERE e.rentado = :rentado")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idEquipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "No_Serial")
    private String noSerial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String serviceTag;
    @Basic(optional = false)
    @NotNull
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    private String rentado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo")
    private Collection<Rentadevolucion> rentadevolucionCollection;
    @JoinColumn(name = "idModelo", referencedColumnName = "Id")
    @ManyToOne
    private Modelos idModelo;
    @JoinColumn(name = "idTecConexion", referencedColumnName = "Id")
    @ManyToOne
    private Tecconexion idTecConexion;
    @JoinColumn(name = "idTipoEquipo", referencedColumnName = "Id")
    @ManyToOne
    private Tipoequipo idTipoEquipo;

    public Equipo() {
    }

    public Equipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Equipo(Integer idEquipo, String descripcion, String noSerial, String serviceTag, boolean estado, String rentado) {
        this.idEquipo = idEquipo;
        this.descripcion = descripcion;
        this.noSerial = noSerial;
        this.serviceTag = serviceTag;
        this.estado = estado;
        this.rentado = rentado;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNoSerial() {
        return noSerial;
    }

    public void setNoSerial(String noSerial) {
        this.noSerial = noSerial;
    }

    public String getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getRentado() {
        return rentado;
    }

    public void setRentado(String rentado) {
        this.rentado = rentado;
    }


    @XmlTransient
    public Collection<Rentadevolucion> getRentadevolucionCollection() {
        return rentadevolucionCollection;
    }

    public void setRentadevolucionCollection(Collection<Rentadevolucion> rentadevolucionCollection) {
        this.rentadevolucionCollection = rentadevolucionCollection;
    }

    public Modelos getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelos idModelo) {
        this.idModelo = idModelo;
    }

    public Tecconexion getIdTecConexion() {
        return idTecConexion;
    }

    public void setIdTecConexion(Tecconexion idTecConexion) {
        this.idTecConexion = idTecConexion;
    }

    public Tipoequipo getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(Tipoequipo idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipo != null ? idEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jd.entities.Equipo[ idEquipo=" + idEquipo + " ]";
    }
    
}
