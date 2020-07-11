package com.jd.controller;

import com.jd.models.Empleado;
import com.jd.models.Rentadevolucion;
import java.util.Collection;
import com.jd.facade.EmpleadoFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "empleadoController")
@ViewScoped
public class EmpleadoController extends AbstractController<Empleado> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isRentadevolucionCollectionEmpty;

    public EmpleadoController() {
        // Inform the Abstract parent controller of the concrete Empleado Entity
        super(Empleado.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRentadevolucionCollectionEmpty();
    }

    public boolean getIsRentadevolucionCollectionEmpty() {
        return this.isRentadevolucionCollectionEmpty;
    }

    private void setIsRentadevolucionCollectionEmpty() {
        Empleado selected = this.getSelected();
        if (selected != null) {
            EmpleadoFacade ejbFacade = (EmpleadoFacade) this.getFacade();
            this.isRentadevolucionCollectionEmpty = ejbFacade.isRentadevolucionCollectionEmpty(selected);
        } else {
            this.isRentadevolucionCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Rentadevolucion entities
     * that are retrieved from Empleado and returns the navigation outcome.
     *
     * @return navigation outcome for Rentadevolucion page
     */
    public String navigateRentadevolucionCollection() {
        Empleado selected = this.getSelected();
        if (selected != null) {
            EmpleadoFacade ejbFacade = (EmpleadoFacade) this.getFacade();
            Collection<Rentadevolucion> selectedRentadevolucionCollection = ejbFacade.findRentadevolucionCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Rentadevolucion_items", selectedRentadevolucionCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/rentadevolucion/index";
    }

}
