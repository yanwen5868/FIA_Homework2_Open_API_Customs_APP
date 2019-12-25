package customs.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import customs.app.dao.PeriodRateDao;
import customs.app.entity.PeriodRate;
import customs.app.service.PeriodRateService;
import customs.app.util.LogUtil;

@Service("PeriodRateService")
public class PeriodRateServiceImpl implements PeriodRateService {

    @Autowired
    private PeriodRateDao PeriodRateDao;
    
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job PeriodRateJob;
            
    public int countByCondition(Map<String, Object> query) {
        return PeriodRateDao.countByCondition(query);
    }

    public List<PeriodRate> listByCondition(Map<String, Object> query) {
        Map<String, Object> map = new HashMap<String, Object>(query);
        return PeriodRateDao.listByCondition(map);
    }

    @Transactional
    public int create(PeriodRate entity) {
        return PeriodRateDao.create(entity);
    }
    
    @Transactional
    public int update(PeriodRate entity) {
        return PeriodRateDao.update(entity);
    }

    public int reset() {
        try {
            Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
            parameters.put("timestamp", new JobParameter(Long.valueOf(System.currentTimeMillis() / 1000)));
            JobExecution jobExecution = jobLauncher.run(PeriodRateJob, new JobParameters(parameters));
            StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
            return stepExecution.getWriteCount();
        } catch (Exception ex) {
            LogUtil.error(ex, ex);
            return 0;
        }
    }

}
