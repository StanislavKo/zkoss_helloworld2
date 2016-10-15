package com.hsd.zkoss.contact.ui.validator;

import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;

import com.hsd.zkoss.contact.ui.model.ContactViewModel;

public class ContactValidator extends PageValidator {

	private static Logger logger = Logger.getLogger(ContactValidator.class);

	public void validate(ValidationContext ctx) {
		// all the bean properties
		Map<String, Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());

		validateRequired(ctx, "firstName", beanProps.get("firstName").getValue() != null ? beanProps.get("firstName").getValue().toString() : null);
		validateRequired(ctx, "lastName", beanProps.get("lastName").getValue() != null ? beanProps.get("lastName").getValue().toString() : null);
		validatePhone(ctx, beanProps.get("phone").getValue() != null ? beanProps.get("phone").getValue().toString() : null);
	}

	private void validatePhone(ValidationContext ctx, String phone) {
		if (phone == null) {
			return;
		}
		if (phone.length() > 0) {
			for (Integer i = 0; i < phone.length(); i++) {
				if (!Character.isDigit(phone.charAt(i))) {
					this.addInvalidMessage(ctx, "phone", Labels.getLabel("error_digits_only"));
					return;
				}
			}
		}
	}
	
}
