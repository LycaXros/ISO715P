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
public class ReporteVentaModel{
    
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
     
}