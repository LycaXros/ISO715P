/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Jesus Dicent
 */
public class ReporteVentaModel implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Date fechaInicio;
    private Date fechaFin;
    private int tipoEquipoId;
    private int marcaId;

    public ReporteVentaModel(Date fechaInicio, Date fechaFin, int tipoEquipoId, int marcaId) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoEquipoId = tipoEquipoId;
        this.marcaId = marcaId;
    }

    public ReporteVentaModel() {
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getTipoEquipoId() {
        return tipoEquipoId;
    }

    public void setTipoEquipoId(int tipoEquipoId) {
        this.tipoEquipoId = tipoEquipoId;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.fechaInicio);
        hash = 61 * hash + Objects.hashCode(this.fechaFin);
        hash = 61 * hash + this.tipoEquipoId;
        hash = 61 * hash + this.marcaId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReporteVentaModel other = (ReporteVentaModel) obj;
        if (this.tipoEquipoId != other.tipoEquipoId) {
            return false;
        }
        if (this.marcaId != other.marcaId) {
            return false;
        }
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        return true;
    }
     
}