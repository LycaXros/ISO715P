/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jesus Dicent
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credencial.findAll", query = "SELECT c FROM Credencial c")
    , @NamedQuery(name = "Credencial.findByIdEmpleado", query = "SELECT c FROM Credencial c WHERE c.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "Credencial.findByUsername", query = "SELECT c FROM Credencial c WHERE c.username = :username")
    , @NamedQuery(name = "Credencial.findByPassword", query = "SELECT c FROM Credencial c WHERE c.password = :password")
    , @NamedQuery(name = "Credencial.findByEstado", query = "SELECT c FROM Credencial c WHERE c.estado = :estado")})
public class Credencial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String password;
    @Basic(optional = false)
    @NotNull
    private boolean estado;
    @JoinColumn(name = "IdEmpleado", referencedColumnName = "idEmpleado", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Empleado empleado;
    @JoinColumn(name = "IdRol", referencedColumnName = "idRol")
    @ManyToOne(optional = false)
    private Rol idRol;

    public Credencial() {
    }

    public Credencial(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Credencial(Integer idEmpleado, String username, String password, boolean estado) {
        this.idEmpleado = idEmpleado;
        this.username = username;
        this.password = password;
        this.estado = estado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credencial)) {
            return false;
        }
        Credencial other = (Credencial) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jd.entities.Credencial[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
