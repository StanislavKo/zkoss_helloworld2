package com.hsd.zkoss.contact.ui.validator;

import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;

import com.hsd.zkoss.contact.ui.model.ContactViewModel;

public class AddressValidator extends PageValidator {

	private static Logger logger = Logger.getLogger(AddressValidator.class);

	public void validate(ValidationContext ctx) {
		// all the bean properties
		Map<String, Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());

		validatePostalCode(ctx, beanProps.get("postalCode").getValue() != null ? beanProps.get("postalCode").getValue().toString() : null);
		validateRequired(ctx, "city", beanProps.get("city").getValue() != null ? beanProps.get("city").getValue().toString() : null);
		validateRequired(ctx, "street", beanProps.get("street").getValue() != null ? beanProps.get("street").getValue().toString() : null);
		validateRequired(ctx, "building", beanProps.get("building").getValue() != null ? beanProps.get("building").getValue().toString() : null);
	}

	private void validatePostalCode(ValidationContext ctx, String postalCode) {
		if (postalCode == null) {
			return;
		}
		if (postalCode.length() > 0) {
			for (Integer i = 0; i < postalCode.length(); i++) {
				if (!Character.isDigit(postalCode.charAt(i))) {
					this.addInvalidMessage(ctx, "postalCode", Labels.getLabel("error_digits_only"));
					return;
				}
			}
		}
	}
	
}
