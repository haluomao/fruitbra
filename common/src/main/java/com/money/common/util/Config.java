package com.money.common.util;

import org.apache.commons.configuration.*;


/**
 * 读取config配置项
 * Created by wangjun04 on 2015/11/13.
 */
public class Config {
    private static CompositeConfiguration compositeConfiguration = null;
    //通用配置节
    private static String configPath = "config.xml";
    //缓存配置节
    private static String cachePath = "cache.xml";
    //个性化配置节
    private static String serviceConfigPath = "config.properties";

    private Config() {

    }
    /**
     * 初始化
     *
     * @throws ConfigurationException
     */
    private static CompositeConfiguration getCompositeConfiguration() throws ConfigurationException {
        if (compositeConfiguration == null) {
            compositeConfiguration = new CompositeConfiguration();
            compositeConfiguration.addConfiguration(new SystemConfiguration());
            //个性化配置节永远需要覆盖默认的配置
            compositeConfiguration.addConfiguration(new PropertiesConfiguration(serviceConfigPath));
            compositeConfiguration.addConfiguration(new XMLConfiguration(configPath));
            compositeConfiguration.addConfiguration(new XMLConfiguration(cachePath));
        }
        return compositeConfiguration;
    }

    /**
     * 获取config配置
     * @param configName 节点名称
     * @return
     * @throws ConfigurationException
     */
    public static String getConfig(String configName) {
        try {
            return getCompositeConfiguration().getString(configName);
        } catch (ConfigurationException ex) {
            LogHelper.error("获取节点{0}出错,ex:{1}", configName, ex);
            return null;
        }
    }

    /**
     * 获取config配置
     * @param configName 节点名称
     * @param defaultValue 默认值
     * @return
     * @throws ConfigurationException
     */
    public static String getConfig(String configName,String defaultValue) {
        String ret = getConfig(configName);
        if (ret == null) {
            return defaultValue;
        }
        return ret;
    }

    /**
     * 获取config配置
     *
     * @param configName   节点名称
     * @param defaultValue 默认值
     * @return
     */
    public static int getConfig(String configName,int defaultValue) {
        String ret = getConfig(configName);
        if (ret == null) {
            return defaultValue;
        }
        return Integer.parseInt(ret);
    }


    /**
     * 获取conifg配置
     * @param configName
     * @param defaultValue
     * @return
     */
    public static long getConfig(String configName,long defaultValue){
        String ret = getConfig(configName);
        if (ret == null) {
            return defaultValue;
        }
        return Long.parseLong(ret);
    }

    /**
     * 获取config配置
     * @param configName
     * @param defaultValue
     * @return
     */
    public static double getConfig(String configName,double defaultValue){
        String ret = getConfig(configName);
        if (ret == null) {
            return defaultValue;
        }
        return Double.parseDouble(ret);
    }


    /**
     * 获取config配置
     * @param configName
     * @param defaultValue
     * @return
     */
    public static boolean getConfig(String configName,boolean defaultValue){
        String ret = getConfig(configName);
        if (ret == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(ret);
    }

}
