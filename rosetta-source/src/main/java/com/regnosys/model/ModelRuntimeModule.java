package com.regnosys.model;

import com.google.inject.AbstractModule;
import com.rosetta.model.lib.validation.ValidatorFactory;
import demo.emissions.functions.Create_Date;
import demo.emissions.functions.Create_DateImpl;

public class ModelRuntimeModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ValidatorFactory.class).to(bindValidatorFactory());
		bind(Create_Date.class).to(bindCreateDate());
	}

	protected Class<? extends ValidatorFactory> bindValidatorFactory() {
		return ValidatorFactory.Default.class;
	}

	protected Class<? extends Create_Date> bindCreateDate() {
		return Create_DateImpl.class;
	}
}
