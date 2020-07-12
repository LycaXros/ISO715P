package com.jd.controller;

import com.jd.entities.Rol;
import com.jd.entities.Credencial;
import java.util.Collection;
import com.jd.facade.RolFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "rolController")
@ViewScoped
public class RolController extends AbstractController<Rol> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isCredencialCollectionEmpty;

    public RolController() {
        // Inform the Abstract parent controller of the concrete Rol Entity
        super(Rol.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCredencialCollectionEmpty();
    }

    public boolean getIsCredencialCollectionEmpty() {
        return this.isCredencialCollectionEmpty;
    }

    private void setIsCredencialCollectionEmpty() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            this.isCredencialCollectionEmpty = ejbFacade.isCredencialCollectionEmpty(selected);
        } else {
            this.isCredencialCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Credencial entities that
     * are retrieved from Rol and returns the navigation outcome.
     *
     * @return navigation outcome for Credencial page
     */
    public String navigateCredencialCollection() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            Collection<Credencial> selectedCredencialCollection = ejbFacade.findCredencialCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Credencial_items", selectedCredencialCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/credencial/index";
    }

}
