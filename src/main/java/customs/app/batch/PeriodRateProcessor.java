package customs.app.batch;

import org.springframework.batch.item.ItemProcessor;

import customs.app.entity.PeriodRate;

public class PeriodRateProcessor implements ItemProcessor<PeriodRate, PeriodRate> {

	public PeriodRate process(PeriodRate itemObj) throws Exception {
		return itemObj;
	}
}
