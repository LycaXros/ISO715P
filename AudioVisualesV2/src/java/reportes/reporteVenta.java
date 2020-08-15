/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jesus Dicent
 */
@Named(value = "reporteVentaBean")
@ViewScoped
public class reporteVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private ReporteVentaModel model;

    public reporteVenta(ReporteVentaModel model) {
        this.model = model;
    }

    public reporteVenta() {
    }

    public ReporteVentaModel getModel() {
        return model;
    }

    public void BuscarDatos(ActionEvent event){
        
    }
}
