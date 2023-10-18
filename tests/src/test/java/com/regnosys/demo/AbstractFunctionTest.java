package com.regnosys.demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;

public class AbstractFunctionTest  {

	private static Injector injector;

	@BeforeEach
	public void setUp() {
		injector = Guice.createInjector(new DemoRuntimeModule());
		injector.injectMembers(this);
	}
}
