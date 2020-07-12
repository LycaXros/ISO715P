package com.jd.controller;

import com.jd.entities.Tipousuario;
import com.jd.entities.Usuario;
import java.util.Collection;
import com.jd.facade.TipousuarioFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipousuarioController")
@ViewScoped
public class TipousuarioController extends AbstractController<Tipousuario> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isUsuarioCollectionEmpty;

    public TipousuarioController() {
        // Inform the Abstract parent controller of the concrete Tipousuario Entity
        super(Tipousuario.class);
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
        Tipousuario selected = this.getSelected();
        if (selected != null) {
            TipousuarioFacade ejbFacade = (TipousuarioFacade) this.getFacade();
            this.isUsuarioCollectionEmpty = ejbFacade.isUsuarioCollectionEmpty(selected);
        } else {
            this.isUsuarioCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Tipousuario and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioCollection() {
        Tipousuario selected = this.getSelected();
        if (selected != null) {
            TipousuarioFacade ejbFacade = (TipousuarioFacade) this.getFacade();
            Collection<Usuario> selectedUsuarioCollection = ejbFacade.findUsuarioCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", selectedUsuarioCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/usuario/index";
    }

}
