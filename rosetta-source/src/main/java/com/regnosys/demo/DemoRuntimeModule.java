package com.regnosys.demo;

import com.google.inject.AbstractModule;
import com.regnosys.rosetta.common.hashing.ReferenceConfig;
import com.regnosys.rosetta.common.postprocess.qualify.QualificationHandler;
import com.regnosys.rosetta.common.postprocess.qualify.QualificationHandlerProvider;
import com.rosetta.model.lib.ModuleConfig;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.validation.ValidatorFactory;

import java.util.Collections;
import java.util.Map;

@ModuleConfig(model="DEMO", type="Rosetta")
public class DemoRuntimeModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(QualifyFunctionFactory.class).to(bindQualifyFunctionFactory());
		bind(QualificationHandlerProvider.class).to(bindQualificationConfigProvider());
		bind(ValidatorFactory.class).to(bindValidatorFactory());
		bind(ReferenceConfig.class).toInstance(ReferenceConfig.noScopeOrExcludedPaths());
	}

	protected Class<? extends QualifyFunctionFactory> bindQualifyFunctionFactory() {
		return QualifyFunctionFactory.Default.class;
	}

	protected Class<? extends QualificationHandlerProvider> bindQualificationConfigProvider() {
		return NoOpQualificationHandlerProvider.class;
	}

	protected Class<? extends ValidatorFactory> bindValidatorFactory() {
		return ValidatorFactory.Default.class;
	}

	public static class NoOpQualificationHandlerProvider implements QualificationHandlerProvider {
		@Override
		public Map<Class<?>, QualificationHandler<?, ?, ?>> getQualificationHandlerMap() {
			return Collections.emptyMap();
		}
	}
}
