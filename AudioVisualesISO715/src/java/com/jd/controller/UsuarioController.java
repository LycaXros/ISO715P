package com.jd.controller;

import com.jd.models.Usuario;
import com.jd.models.Rentadevolucion;
import java.util.Collection;
import com.jd.facade.UsuarioFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

    @Inject
    private TipopersonaController idTipoPersonaController;
    @Inject
    private TipousuarioController idTipoUsuarioController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isRentadevolucionCollectionEmpty;

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(Usuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idTipoPersonaController.setSelected(null);
        idTipoUsuarioController.setSelected(null);
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
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isRentadevolucionCollectionEmpty = ejbFacade.isRentadevolucionCollectionEmpty(selected);
        } else {
            this.isRentadevolucionCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Rentadevolucion entities
     * that are retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Rentadevolucion page
     */
    public String navigateRentadevolucionCollection() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            Collection<Rentadevolucion> selectedRentadevolucionCollection = ejbFacade.findRentadevolucionCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Rentadevolucion_items", selectedRentadevolucionCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/rentadevolucion/index";
    }

    /**
     * Sets the "selected" attribute of the Tipopersona controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoPersona(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && idTipoPersonaController.getSelected() == null) {
            idTipoPersonaController.setSelected(selected.getIdTipoPersona());
        }
    }

    /**
     * Sets the "selected" attribute of the Tipousuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoUsuario(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && idTipoUsuarioController.getSelected() == null) {
            idTipoUsuarioController.setSelected(selected.getIdTipoUsuario());
        }
    }

}
