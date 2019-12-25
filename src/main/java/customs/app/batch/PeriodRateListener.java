package customs.app.batch;

import java.io.StringReader;

import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import customs.app.util.DbUtil;
import customs.app.util.LogUtil;


public class PeriodRateListener implements StepExecutionListener {
    
    private DataSource dataSource;

    public void beforeStep(StepExecution stepExecution) {
        try {
            System.out.println("befor step");
            DbUtil.execute(dataSource, new StringReader("truncate table PeriodRate;"));
            System.out.println("complete truncate table PeriodRate");
        } catch (Exception ex) {
            LogUtil.error(ex, ex);
        }
    }

    public ExitStatus afterStep(StepExecution stepExecution) {
        try {
//            System.out.println("after step");
//            if (ExitStatus.COMPLETED.equals(stepExecution.getExitStatus())) {
//                System.out.println("begin truncate and insert");
//                DbUtil.execute(dataSource, new StringReader("truncate table APPTNON_BUSINESS_UNIT;"));
//                DbUtil.execute(dataSource, new StringReader("insert into APPTNON_BUSINESS_UNIT select * from APPWNON_BUSINESS_UNIT;"));
//                System.out.println("complete insert table APPTNON_BUSINESS_UNIT");
//            }
            return ExitStatus.COMPLETED;
        } catch (Exception ex) {
            LogUtil.error(ex, ex);
            return ExitStatus.FAILED;
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    

}
