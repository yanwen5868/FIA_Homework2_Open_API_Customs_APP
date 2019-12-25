package customs.app.util;

import org.apache.log4j.Logger;

public class LogUtil {
    
    private static transient Logger log = Logger.getLogger(LogUtil.class);

    /**
     * 送出info 訊息
     * @param message 訊息
     */
    public static void info(Object message) {
        log.info(message);
    }
    
    /**
     * 送出INFO 訊息
     * @param message 訊息
     * @param t 異常事件
     */
    public static void info(Object message, Throwable t) {
        log.info(message, t);
    }
    
    /**
     * 送出WARNING 訊息
     * @param message 訊息
     */
    public static void warn(Object message) {
        log.warn(message);
    }
    
    /**
     * 送出WARNING 訊息
     * @param message 訊息
     * @param t 異常事件
     */
    public static void warn(Object message, Throwable t) {
        log.warn(message, t);
    }
    
    /**
     * 送出ERROR 訊息
     * @param message 訊息
     */
    public static void error(Object message) {
        log.error(message);
    }
    
    /**
     * 送出ERROR 訊息
     * @param message 訊息
     * @param t 異常事件
     */
    public static void error(Object message, Throwable t) {
        log.error(message, t);
    }
    
}
