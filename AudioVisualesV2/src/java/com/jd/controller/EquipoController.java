package com.jd.controller;

import com.jd.entities.Equipo;
import com.jd.entities.Rentadevolucion;
import java.util.Collection;
import com.jd.facade.EquipoFacade;
import com.jd.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "equipoController")
@ViewScoped
public class EquipoController extends AbstractController<Equipo> {

    private Collection<Equipo> itemsNoRentados;
    private Collection<Equipo> itemsRentados;
    
    @Inject
    private ModelosController idModeloController;
    @Inject
    private TecconexionController idTecConexionController;
    @Inject
    private TipoequipoController idTipoEquipoController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isRentadevolucionCollectionEmpty;

    public EquipoController() {
        // Inform the Abstract parent controller of the concrete Equipo Entity
        super(Equipo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idModeloController.setSelected(null);
        idTecConexionController.setSelected(null);
        idTipoEquipoController.setSelected(null);
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
        Equipo selected = this.getSelected();
        if (selected != null) {
            EquipoFacade ejbFacade = (EquipoFacade) this.getFacade();
            this.isRentadevolucionCollectionEmpty = ejbFacade.isRentadevolucionCollectionEmpty(selected);
        } else {
            this.isRentadevolucionCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Rentadevolucion entities
     * that are retrieved from Equipo and returns the navigation outcome.
     *
     * @return navigation outcome for Rentadevolucion page
     */
    public String navigateRentadevolucionCollection() {
        Equipo selected = this.getSelected();
        if (selected != null) {
            EquipoFacade ejbFacade = (EquipoFacade) this.getFacade();
            Collection<Rentadevolucion> selectedRentadevolucionCollection = ejbFacade.findRentadevolucionCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Rentadevolucion_items", selectedRentadevolucionCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/rentadevolucion/index";
    }

    /**
     * Sets the "selected" attribute of the Modelos controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdModelo(ActionEvent event) {
        Equipo selected = this.getSelected();
        if (selected != null && idModeloController.getSelected() == null) {
            idModeloController.setSelected(selected.getIdModelo());
        }
    }

    /**
     * Sets the "selected" attribute of the Tecconexion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTecConexion(ActionEvent event) {
        Equipo selected = this.getSelected();
        if (selected != null && idTecConexionController.getSelected() == null) {
            idTecConexionController.setSelected(selected.getIdTecConexion());
        }
    }

    /**
     * Sets the "selected" attribute of the Tipoequipo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoEquipo(ActionEvent event) {
        Equipo selected = this.getSelected();
        if (selected != null && idTipoEquipoController.getSelected() == null) {
            idTipoEquipoController.setSelected(selected.getIdTipoEquipo());
        }
    }

    public Collection<Equipo> getItemsNoRentados() {
        if(itemsNoRentados == null){
            EquipoFacade ef = (EquipoFacade) this.getFacade();
            itemsNoRentados = ef.getEquiposNoRentados();            
        }        
        return itemsNoRentados;
    }

    public Collection<Equipo> getItemsRentados() {
        if(itemsRentados == null){
            EquipoFacade ef = (EquipoFacade) this.getFacade();
            itemsRentados = ef.getEquiposRentados();            
        }        
        return itemsRentados;
    }
 
}
