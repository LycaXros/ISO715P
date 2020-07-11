package com.jd.controller;

import com.jd.models.Tipoequipo;
import com.jd.models.Equipo;
import java.util.Collection;
import com.jd.facade.TipoequipoFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoequipoController")
@ViewScoped
public class TipoequipoController extends AbstractController<Tipoequipo> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEquipoCollectionEmpty;

    public TipoequipoController() {
        // Inform the Abstract parent controller of the concrete Tipoequipo Entity
        super(Tipoequipo.class);
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
        Tipoequipo selected = this.getSelected();
        if (selected != null) {
            TipoequipoFacade ejbFacade = (TipoequipoFacade) this.getFacade();
            this.isEquipoCollectionEmpty = ejbFacade.isEquipoCollectionEmpty(selected);
        } else {
            this.isEquipoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Equipo entities that are
     * retrieved from Tipoequipo and returns the navigation outcome.
     *
     * @return navigation outcome for Equipo page
     */
    public String navigateEquipoCollection() {
        Tipoequipo selected = this.getSelected();
        if (selected != null) {
            TipoequipoFacade ejbFacade = (TipoequipoFacade) this.getFacade();
            Collection<Equipo> selectedEquipoCollection = ejbFacade.findEquipoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Equipo_items", selectedEquipoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/equipo/index";
    }

}
