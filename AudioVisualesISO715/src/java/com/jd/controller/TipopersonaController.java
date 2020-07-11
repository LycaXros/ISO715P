package com.jd.controller;

import com.jd.models.Tipopersona;
import com.jd.models.Usuario;
import java.util.Collection;
import com.jd.facade.TipopersonaFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipopersonaController")
@ViewScoped
public class TipopersonaController extends AbstractController<Tipopersona> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isUsuarioCollectionEmpty;

    public TipopersonaController() {
        // Inform the Abstract parent controller of the concrete Tipopersona Entity
        super(Tipopersona.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsUsuarioCollectionEmpty();
    }

    public boolean getIsUsuarioCollectionEmpty() {
        return this.isUsuarioCollectionEmpty;
    }

    private void setIsUsuarioCollectionEmpty() {
        Tipopersona selected = this.getSelected();
        if (selected != null) {
            TipopersonaFacade ejbFacade = (TipopersonaFacade) this.getFacade();
            this.isUsuarioCollectionEmpty = ejbFacade.isUsuarioCollectionEmpty(selected);
        } else {
            this.isUsuarioCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Tipopersona and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioCollection() {
        Tipopersona selected = this.getSelected();
        if (selected != null) {
            TipopersonaFacade ejbFacade = (TipopersonaFacade) this.getFacade();
            Collection<Usuario> selectedUsuarioCollection = ejbFacade.findUsuarioCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", selectedUsuarioCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/usuario/index";
    }

}
