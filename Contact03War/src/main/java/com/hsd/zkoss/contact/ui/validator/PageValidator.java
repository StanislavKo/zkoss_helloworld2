package com.hsd.zkoss.contact.ui.validator;

import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;

import com.hsd.zkoss.contact.ui.model.ContactViewModel;

public abstract class PageValidator extends AbstractValidator {

	private static Logger logger = Logger.getLogger(PageValidator.class);

	protected void validateRequired(ValidationContext ctx, String key, String value) {
		if (value == null) {
			this.addInvalidMessage(ctx, key, Labels.getLabel("error_required"));
		}
	}
	
}
