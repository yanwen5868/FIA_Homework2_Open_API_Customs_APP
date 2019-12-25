package customs.app.dao;

import java.util.List;
import java.util.Map;

import customs.app.entity.PeriodRate;


public interface PeriodRateDao {
 
    int create(PeriodRate entity);
    
    int update(PeriodRate entity);
        
    int countAll();
    
    List<PeriodRate> listAll();
    
    int countByCondition(Map<String, Object> map);
    
    List<PeriodRate> listByCondition(Map<String, Object> map);
    
}
