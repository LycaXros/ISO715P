package com.jd.controller;

import com.jd.models.Modelos;
import com.jd.models.Equipo;
import java.util.Collection;
import com.jd.facade.ModelosFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "modelosController")
@ViewScoped
public class ModelosController extends AbstractController<Modelos> {

    @Inject
    private MarcasController idMarcaController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEquipoCollectionEmpty;

    public ModelosController() {
        // Inform the Abstract parent controller of the concrete Modelos Entity
        super(Modelos.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idMarcaController.setSelected(null);
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
        Modelos selected = this.getSelected();
        if (selected != null) {
            ModelosFacade ejbFacade = (ModelosFacade) this.getFacade();
            this.isEquipoCollectionEmpty = ejbFacade.isEquipoCollectionEmpty(selected);
        } else {
            this.isEquipoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Equipo entities that are
     * retrieved from Modelos and returns the navigation outcome.
     *
     * @return navigation outcome for Equipo page
     */
    public String navigateEquipoCollection() {
        Modelos selected = this.getSelected();
        if (selected != null) {
            ModelosFacade ejbFacade = (ModelosFacade) this.getFacade();
            Collection<Equipo> selectedEquipoCollection = ejbFacade.findEquipoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Equipo_items", selectedEquipoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/equipo/index";
    }

    /**
     * Sets the "selected" attribute of the Marcas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdMarca(ActionEvent event) {
        Modelos selected = this.getSelected();
        if (selected != null && idMarcaController.getSelected() == null) {
            idMarcaController.setSelected(selected.getIdMarca());
        }
    }

}
