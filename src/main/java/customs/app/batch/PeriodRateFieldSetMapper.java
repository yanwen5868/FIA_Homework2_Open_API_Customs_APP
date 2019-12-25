package customs.app.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import customs.app.entity.PeriodRate;

public class PeriodRateFieldSetMapper implements FieldSetMapper<PeriodRate> {

	public PeriodRate mapFieldSet(FieldSet fieldSetObj) throws BindException {
	    PeriodRate obj = new PeriodRate();
		obj.setCurrency(fieldSetObj.readString(0));
		obj.setYear(Integer.parseInt(fieldSetObj.readString(1)));
		obj.setMonth(Integer.parseInt(fieldSetObj.readString(2)));
		obj.setPeriod(Integer.parseInt(fieldSetObj.readString(3)));
		obj.setBuyPrice(Double.parseDouble(fieldSetObj.readString(4)));
		obj.setSellPrice(Double.parseDouble(fieldSetObj.readString(5)));
		return obj;
	    
	}
}
