package com.regnosys.model;

import com.google.inject.AbstractModule;
import com.regnosys.rosetta.common.validation.RosettaTypeValidator;
import com.rosetta.model.lib.validation.ModelObjectValidator;
import com.rosetta.model.lib.validation.ValidatorFactory;

public class ModelRuntimeModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ValidatorFactory.class).to(bindValidatorFactory());
		bind(ModelObjectValidator.class).to(bindModelObjectValidator());
	}


	protected Class<? extends ValidatorFactory> bindValidatorFactory() {
		return ValidatorFactory.Default.class;
	}

	protected Class<? extends ModelObjectValidator> bindModelObjectValidator() {
		return RosettaTypeValidator.class;
	}

}
