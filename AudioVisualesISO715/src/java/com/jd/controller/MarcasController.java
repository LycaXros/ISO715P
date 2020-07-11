package com.jd.controller;

import com.jd.models.Marcas;
import com.jd.models.Modelos;
import java.util.Collection;
import com.jd.facade.MarcasFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "marcasController")
@ViewScoped
public class MarcasController extends AbstractController<Marcas> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isModelosCollectionEmpty;

    public MarcasController() {
        // Inform the Abstract parent controller of the concrete Marcas Entity
        super(Marcas.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsModelosCollectionEmpty();
    }

    public boolean getIsModelosCollectionEmpty() {
        return this.isModelosCollectionEmpty;
    }

    private void setIsModelosCollectionEmpty() {
        Marcas selected = this.getSelected();
        if (selected != null) {
            MarcasFacade ejbFacade = (MarcasFacade) this.getFacade();
            this.isModelosCollectionEmpty = ejbFacade.isModelosCollectionEmpty(selected);
        } else {
            this.isModelosCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Modelos entities that are
     * retrieved from Marcas and returns the navigation outcome.
     *
     * @return navigation outcome for Modelos page
     */
    public String navigateModelosCollection() {
        Marcas selected = this.getSelected();
        if (selected != null) {
            MarcasFacade ejbFacade = (MarcasFacade) this.getFacade();
            Collection<Modelos> selectedModelosCollection = ejbFacade.findModelosCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Modelos_items", selectedModelosCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/modelos/index";
    }

}
