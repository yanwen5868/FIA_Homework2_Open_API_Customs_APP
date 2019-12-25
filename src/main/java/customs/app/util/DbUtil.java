package customs.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.ibatis.jdbc.ScriptRunner;

public class DbUtil {
    
    public static final String KEY_LOG = "log";
    
    public static final String KEY_ERROR_LOG = "errorLog";
    
    /**
     * 關閉連接
     * @param con 連線
     */
    public static void close(Connection con) {
        if (con != null) {
            try { 
                con.close();
            } catch (SQLException e) {
                LogUtil.error(e, e);
            }
        }
    }
        
    /**
     * 關閉連接
     * @param stmt 表達式
     * @param con 連線
     * @throws Exception 異常事件
     */
    public static void close(Statement stmt,Connection con) throws Exception {
        if (stmt != null) {
            try {
                stmt.close();    
            } catch (SQLException e) {
                LogUtil.error(e, e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                LogUtil.error(e, e);
            }
        }
    }
    
    /**
     * 關閉連接
     * @param pstmt 表達式
     * @param con 連線
     */
    public static void close(PreparedStatement pstmt,Connection con) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                LogUtil.error(e, e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                LogUtil.error(e, e);
            }
        }
    }
    
    /**
     * 關閉連接
     * @param cstmt 表達式
     * @param con 連線
     */
    public static void close(CallableStatement cstmt,Connection con) {
        if (cstmt != null) {
            try {
                cstmt.close();
            } catch (SQLException e) {
                LogUtil.error(e, e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                LogUtil.error(e, e);
            }
        }
    }
    
    /**
     * 
     * @param dataSource dataSource
     * @param in in
     * @return map
     * @throws Exception 異常事件
     */
    public static Map<String, String> execute(DataSource dataSource, InputStream in) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        return execute(dataSource, reader);
    }

    /**
     * 
     * @param dataSource dataSource
     * @param reader reader
     * @return map
     * @throws Exception 異常事件
     */
    public static Map<String, String> execute(DataSource dataSource, Reader reader) throws Exception {
        Connection con = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            con = dataSource.getConnection();
            ScriptRunner runner = new ScriptRunner(con);
            runner.setAutoCommit(true);
            runner.setStopOnError(false);
            runner.setDelimiter(";");
            runner.setSendFullScript(false);
            runner.setFullLineDelimiter(false);
            StringBuilderWriter logWriter = new StringBuilderWriter();
            StringBuilderWriter errorLogWriter = new StringBuilderWriter();
            runner.setLogWriter(new PrintWriter(logWriter));
            runner.setErrorLogWriter(new PrintWriter(errorLogWriter));
            runner.runScript(reader);
            map.put(KEY_ERROR_LOG, logWriter.toString());
            map.put(KEY_ERROR_LOG, errorLogWriter.toString());
        } catch (Exception e) {
            LogUtil.error(e, e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                LogUtil.error(e, e);
            }
            DbUtil.close(con);
        }
        return map;
    }

   
}
