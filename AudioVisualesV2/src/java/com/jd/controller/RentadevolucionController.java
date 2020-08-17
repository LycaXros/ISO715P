package com.jd.controller;

import com.jd.entities.Rentadevolucion;
import com.jd.facade.RentadevolucionFacade;
import com.jd.controller.util.MobilePageController;
import com.jd.entities.Equipo;
import com.jd.facade.EquipoFacade;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "rentadevolucionController")
@ViewScoped
public class RentadevolucionController extends AbstractController<Rentadevolucion> {

    @EJB
    private EquipoFacade equipoFacade;

    private Collection<Rentadevolucion> itemsRentados;

    @Inject
    private EmpleadoController idEmpleadoController;
    @Inject
    private EquipoController idEquipoController;
    @Inject
    private UsuarioController idUsuarioController;
    @Inject
    private MobilePageController mobilePageController;

    public RentadevolucionController() {
        // Inform the Abstract parent controller of the concrete Rentadevolucion Entity
        super(Rentadevolucion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idEmpleadoController.setSelected(null);
        idEquipoController.setSelected(null);
        idUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEmpleado(ActionEvent event) {
        Rentadevolucion selected = this.getSelected();
        if (selected != null && idEmpleadoController.getSelected() == null) {
            idEmpleadoController.setSelected(selected.getIdEmpleado());
        }
    }

    /**
     * Sets the "selected" attribute of the Equipo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEquipo(ActionEvent event) {
        Rentadevolucion selected = this.getSelected();
        if (selected != null && idEquipoController.getSelected() == null) {
            idEquipoController.setSelected(selected.getIdEquipo());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUsuario(ActionEvent event) {
        Rentadevolucion selected = this.getSelected();
        if (selected != null && idUsuarioController.getSelected() == null) {
            idUsuarioController.setSelected(selected.getIdUsuario());
        }
    }

    public Collection<Rentadevolucion> getItemsRentados() {
        if (itemsRentados == null) {
            RentadevolucionFacade ef = (RentadevolucionFacade) this.getFacade();
            itemsRentados = ef.getRentados();
        }
        return itemsRentados;
    }
    
    public void beforeSaveNew(ActionEvent e){
        this.getSelected().setFechaPrestamo(new Date());
        this.saveNew(e);
    }
    
    public void retornar(ActionEvent event) {
        
        Equipo equipo = this.getSelected().getIdEquipo();
        equipo.setRentado("N");
        
        this.getSelected().setFechaDevolucion(new Date());
        String comentario = this.getSelected().getComentario() + ". Devuelto!!!!!";
        this.getSelected().setComentario(comentario);
        this.getSelected().setEstado(false);
        
        equipoFacade.edit(equipo);
        this.getFacade().edit(this.getSelected());
    }
}
