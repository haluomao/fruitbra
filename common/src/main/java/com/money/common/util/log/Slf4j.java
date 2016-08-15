package com.money.common.util.log;

import com.money.common.util.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 描述：
 * 作者：wangjun04
 * 日期：2016/1/8
 * 版本：v1.0
 * 项目：qdService
 * 文件：
 * 重要：
 */
public class Slf4j extends ILog {
    private static Logger logger = null;

    /**
     * 构造方法
     */
    public Slf4j() {
        super();
        if (logger == null)
            logger = LoggerFactory.getLogger(this.getClass());
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