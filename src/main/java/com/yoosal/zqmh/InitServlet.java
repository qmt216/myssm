package com.yoosal.zqmh;


import com.yoosal.zqmh.cache.CacheUtil;
import com.yoosal.zqmh.util.search.ElasticSearchHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 秦明涛 on 2015/5/7.
 * Desc 初始化操作
 */
public class InitServlet implements InitializingBean, DisposableBean {
    public static Properties config;
    private static Logger logger = Logger.getLogger(InitServlet.class);

    private Resource cacheLocations;
    private Resource uploadConfigLocations;

    public void afterPropertiesSet() throws Exception {
        try {
            CacheUtil.loadCacheConfig(cacheLocations.getInputStream());
            logger.warn("加载缓存完成");
            config = new Properties();
            InputStream fis = uploadConfigLocations.getInputStream();
            config.load(fis);
            fis.close();
            logger.warn("加载配置信息完成");
        } catch (Exception e) {
            logger.error("加载缓存配置出错");
        }
    }

    public void destroy() throws Exception {
        ElasticSearchHandler.close();
    }

    public void setCacheLocations(Resource cacheLocations) {
        this.cacheLocations = cacheLocations;
    }

    public void setUploadConfigLocations(Resource uploadConfigLocations) {
        this.uploadConfigLocations = uploadConfigLocations;
    }
}
