package com.money.common.util.log;

import com.money.common.util.Consts;
import org.apache.log4j.Logger;

/**
 * Created by wangjun04 on 2015/11/13.
 */
public class Log4j extends ILog {
    private static Logger logger = null;

    public Log4j() {
        super();
        if (logger == null)
            logger = Logger.getLogger(this.getClass());
    }

    /**
     * Info日志记录
     *
     * @param message
     * @return
     */
    public int info(String message) {
        try {
            logger.info(message);
            return Consts.SUCCESS;
        } catch (Exception ex) {
            return Consts.ERROR;
        }
    }

    public int info(String message, Throwable throwable) {
        try {
            logger.info(message, throwable);
            return Consts.SUCCESS;
        } catch (Exception ex) {
            return Consts.ERROR;
        }
    }

    /**
     * Error 日志记录
     *
     * @param message
     * @return
     */
    public int error(String message) {
        try {
            logger.error(message);
            return Consts.SUCCESS;
        } catch (Exception ex) {
            return Consts.ERROR;
        }
    }

    public int error(String message, Throwable throwable) {
        try {
            logger.error(message, throwable);
            return Consts.SUCCESS;
        } catch (Exception ex) {
            return Consts.ERROR;
        }
    }


    /**
     * Debug 日志记录
     *
     * @param message
     * @return
     */
    public int debug(String message) {
        try {
            logger.debug(message);
            return Consts.SUCCESS;
        } catch (Exception ex) {
            return Consts.ERROR;
        }
    }

    public int debug(String message, Throwable throwable) {
        try {
            logger.debug(message, throwable);
            return Consts.SUCCESS;
        } catch (Exception ex) {
            return Consts.ERROR;
        }
    }

    /**
     * Warn 日志记录
     *
     * @param message
     * @return
     */
    public int warn(String message) {
        try {
            logger.warn(message);
            return Consts.SUCCESS;
        } catch (Exception ex) {
            return Consts.ERROR;
        }
    }

    public int warn(String message, Throwable throwable) {
        try {
            logger.warn(message, throwable);
            return Consts.SUCCESS;
        } catch (Exception ex) {
            return Consts.ERROR;
        }
    }
}
