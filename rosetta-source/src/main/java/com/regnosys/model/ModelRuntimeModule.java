package com.regnosys.model;

import com.google.inject.AbstractModule;
import com.regnosys.rosetta.common.postprocess.qualify.QualificationHandlerProvider;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.validation.ValidatorFactory;
import demo.emissions.functions.Create_Date;
import demo.emissions.functions.Create_DateImpl;
import org.isda.cdm.qualify.CdmQualificationHandlerProvider;

public class ModelRuntimeModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ValidatorFactory.class).to(bindValidatorFactory());
		bind(Create_Date.class).to(bindCreateDate());
		bind(QualifyFunctionFactory.class).to(bindQualifyFunctionFactory());
		bind(QualificationHandlerProvider.class).to(bindQualificationConfigProvider());
	}

	protected Class<? extends ValidatorFactory> bindValidatorFactory() {
		return ValidatorFactory.Default.class;
	}

	protected Class<? extends Create_Date> bindCreateDate() {
		return Create_DateImpl.class;
	}

	protected Class<? extends QualifyFunctionFactory> bindQualifyFunctionFactory() {
		return QualifyFunctionFactory.Default.class;
	}

	protected Class<? extends QualificationHandlerProvider> bindQualificationConfigProvider() {
		return CdmQualificationHandlerProvider.class;
	}

}
