/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import com.jd.entities.Rentadevolucion;
import com.jd.facade.AbstractFacade;
import com.jd.facade.RentadevolucionFacade;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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

    @Inject
    private RentadevolucionFacade ejb;

    private Collection<Rentadevolucion> items;

    public Collection<Rentadevolucion> getItems() {
        return items;
    }

    public void setItems(Collection<Rentadevolucion> items) {
        this.items = items;
    }

    private static final long serialVersionUID = 1L;

    private ReporteVentaModel model;

    public reporteVenta(ReporteVentaModel model) {
        this.model = model;
    }

    public reporteVenta() {
        if (this.model == null) {
            this.model = new ReporteVentaModel();
        }
    }

    public ReporteVentaModel getModel() {
        return model;
    }

    public void BuscarDatos(ActionEvent event) {

        this.items = ejb.GetResult(this.model);
        
    }
}
