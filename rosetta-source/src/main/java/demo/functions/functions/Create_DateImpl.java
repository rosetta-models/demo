package demo.functions.functions;

import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.records.DateImpl;

public class Create_DateImpl extends Create_Date {

	@Override
	protected Date doEvaluate(Integer year, Integer month, Integer day) {
		return DateImpl.of(year, month, day);
	}
}
