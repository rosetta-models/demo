package com.regnosys.model;

import com.google.inject.AbstractModule;
import demo.functions.functions.Create_Date;
import demo.functions.functions.Create_DateImpl;
import org.jetbrains.annotations.NotNull;

public class ModelRuntimeModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Create_Date.class).to(bindCreateDate());
	}

	@NotNull
	protected Class<? extends Create_Date> bindCreateDate() {
		return Create_DateImpl.class;
	}
}
