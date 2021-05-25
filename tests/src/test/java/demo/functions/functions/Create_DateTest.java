package demo.functions.functions;

import com.regnosys.model.AbstractFunctionTest;
import com.rosetta.model.lib.records.Date;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

class Create_DateTest extends AbstractFunctionTest {

	@Inject
	@SuppressWarnings("unused")
	private Create_Date func;

	@Test
	void shouldCreateDate() {
		Date date = func.evaluate(2021, 5, 25);

		assertEquals(2021, date.getYear());
		assertEquals(5, date.getMonth());
		assertEquals(25, date.getDay());
	}
}