/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jesus Dicent
 */
@Named(value = "reporteVentaBean")
@ViewScoped
public class reporteVenta {
    
    @Inject
    private ReporteVentaModel model;

    public reporteVenta() {
    }

    public ReporteVentaModel getModel() {
        return model;
    }
    
    
}
