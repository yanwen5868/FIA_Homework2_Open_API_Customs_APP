package customs.app.service;

import java.util.List;
import java.util.Map;

import customs.app.entity.PeriodRate;

public interface PeriodRateService {

    int create(PeriodRate entity);
    
    int update(PeriodRate entity);

    int countByCondition(Map<String, Object> query);
    
    List<PeriodRate> listByCondition(Map<String, Object> query);
    
    int reset();
    
}
