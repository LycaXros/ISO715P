package com.jd.controller;

import com.jd.models.Credencial;
import com.jd.facade.CredencialFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "credencialController")
@ViewScoped
public class CredencialController extends AbstractController<Credencial> {

    @Inject
    private EmpleadoController empleadoController;
    @Inject
    private RolController idRolController;
    @Inject
    private MobilePageController mobilePageController;

    public CredencialController() {
        // Inform the Abstract parent controller of the concrete Credencial Entity
        super(Credencial.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoController.setSelected(null);
        idRolController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleado(ActionEvent event) {
        Credencial selected = this.getSelected();
        if (selected != null && empleadoController.getSelected() == null) {
            empleadoController.setSelected(selected.getEmpleado());
        }
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdRol(ActionEvent event) {
        Credencial selected = this.getSelected();
        if (selected != null && idRolController.getSelected() == null) {
            idRolController.setSelected(selected.getIdRol());
        }
    }

}
