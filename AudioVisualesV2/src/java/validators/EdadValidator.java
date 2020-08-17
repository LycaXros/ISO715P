/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

/**
 *
 * @author Jesus Dicent
 */
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validators.EdadValidator")
public class EdadValidator implements Validator{

	private static final int EDADMINIMA = 18;
	private static final int EDADMAXIMA = 99;
        	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		if(Integer.parseInt(value.toString()) < EDADMINIMA ||
                   Integer.parseInt(value.toString()) > EDADMAXIMA){
			
			FacesMessage msg = 
				new FacesMessage("Edad incorrecto.", 
						"Valor invalido.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	}
}