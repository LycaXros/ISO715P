package com.jd.controller;

import com.jd.models.Tecconexion;
import com.jd.models.Equipo;
import java.util.Collection;
import com.jd.facade.TecconexionFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tecconexionController")
@ViewScoped
public class TecconexionController extends AbstractController<Tecconexion> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEquipoCollectionEmpty;

    public TecconexionController() {
        // Inform the Abstract parent controller of the concrete Tecconexion Entity
        super(Tecconexion.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEquipoCollectionEmpty();
    }

    public boolean getIsEquipoCollectionEmpty() {
        return this.isEquipoCollectionEmpty;
    }

    private void setIsEquipoCollectionEmpty() {
        Tecconexion selected = this.getSelected();
        if (selected != null) {
            TecconexionFacade ejbFacade = (TecconexionFacade) this.getFacade();
            this.isEquipoCollectionEmpty = ejbFacade.isEquipoCollectionEmpty(selected);
        } else {
            this.isEquipoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Equipo entities that are
     * retrieved from Tecconexion and returns the navigation outcome.
     *
     * @return navigation outcome for Equipo page
     */
    public String navigateEquipoCollection() {
        Tecconexion selected = this.getSelected();
        if (selected != null) {
            TecconexionFacade ejbFacade = (TecconexionFacade) this.getFacade();
            Collection<Equipo> selectedEquipoCollection = ejbFacade.findEquipoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Equipo_items", selectedEquipoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/equipo/index";
    }

}
