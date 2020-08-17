/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Rentadevolucion.findAll", query = "SELECT r FROM Rentadevolucion r")
    , @NamedQuery(name = "Rentadevolucion.findByNoPrestamo", query = "SELECT r FROM Rentadevolucion r WHERE r.noPrestamo = :noPrestamo")
    , @NamedQuery(name = "Rentadevolucion.findByFechaPrestamo", query = "SELECT r FROM Rentadevolucion r WHERE r.fechaPrestamo = :fechaPrestamo")
    , @NamedQuery(name = "Rentadevolucion.betweenFechaPrestamo", query = "SELECT r FROM Rentadevolucion r WHERE r.fechaPrestamo between :fechaIn and :fechaFin")
    , @NamedQuery(name = "Rentadevolucion.findByFechaDevolucion", query = "SELECT r FROM Rentadevolucion r WHERE r.fechaDevolucion = :fechaDevolucion")
    , @NamedQuery(name = "Rentadevolucion.findByComentario", query = "SELECT r FROM Rentadevolucion r WHERE r.comentario = :comentario")
    , @NamedQuery(name = "Rentadevolucion.findByEstado", query = "SELECT r FROM Rentadevolucion r WHERE r.estado = :estado")
    , @NamedQuery(name = "Rentadevolucion.findRentados", query = "SELECT r FROM Rentadevolucion r WHERE r.idEquipo.rentado = 'S' AND r.estado = 1")})
public class Rentadevolucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "No_Prestamo")
    private Integer noPrestamo;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fechaPrestamo;
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    private String comentario;
    @Basic(optional = false)
    @NotNull
    private boolean estado;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado")
    @ManyToOne(optional = false)
    private Empleado idEmpleado;
    @JoinColumn(name = "idEquipo", referencedColumnName = "idEquipo")
    @ManyToOne(optional = false)
    private Equipo idEquipo;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Rentadevolucion() {
    }

    public Rentadevolucion(Integer noPrestamo) {
        this.noPrestamo = noPrestamo;
    }

    public Rentadevolucion(Integer noPrestamo, Date fechaPrestamo, String comentario, boolean estado) {
        this.noPrestamo = noPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.comentario = comentario;
        this.estado = estado;
    }

    public Integer getNoPrestamo() {
        return noPrestamo;
    }

    public void setNoPrestamo(Integer noPrestamo) {
        this.noPrestamo = noPrestamo;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noPrestamo != null ? noPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rentadevolucion)) {
            return false;
        }
        Rentadevolucion other = (Rentadevolucion) object;
        if ((this.noPrestamo == null && other.noPrestamo != null) || (this.noPrestamo != null && !this.noPrestamo.equals(other.noPrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jd.entities.Rentadevolucion[ noPrestamo=" + noPrestamo + " ]";
    }
    
}
