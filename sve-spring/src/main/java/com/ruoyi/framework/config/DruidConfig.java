package com.ruoyi.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.config.properties.DruidProperties;
import com.ruoyi.framework.datasource.DynamicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.servlet.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * druid 配置多数据源
 * 
 * @author ruoyi
 */
@Configuration
public class DruidConfig
{
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DruidProperties druidProperties)
    {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(DruidProperties druidProperties)
    {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }


    @Bean
    @ConfigurationProperties("spring.datasource.druid.outwater")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.outwater", name = "enabled", havingValue = "true")
    public DataSource outwaterDataSource(DruidProperties druidProperties)
    {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.lwjh")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.lwjh", name = "enabled", havingValue = "true")
    public DataSource lwjhDataSource(DruidProperties druidProperties)
    {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.guocheng")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.guocheng", name = "enabled", havingValue = "true")
    public  DataSource guoChengSource(DruidProperties druidProperties){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.voc")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.voc", name = "enabled", havingValue = "true")
    public  DataSource vocDataSource(DruidProperties druidProperties){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.youyan")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.youyan", name = "enabled", havingValue = "true")
    public  DataSource youyanDataSource(DruidProperties druidProperties){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.jiayou")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.jiayou", name = "enabled", havingValue = "true")
    public  DataSource jiayouDataSource(DruidProperties druidProperties){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.qixiu")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.qixiu", name = "enabled", havingValue = "true")
    public  DataSource qixiuDataSource(DruidProperties druidProperties){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.jinshu")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.jinshu", name = "enabled", havingValue = "true")
    public  DataSource jinshuDataSource(DruidProperties druidProperties){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.lingxing")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.lingxing", name = "enabled", havingValue = "true")
    public  DataSource lingxingDataSource(DruidProperties druidProperties){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.zaozhi")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.zaozhi", name = "enabled", havingValue = "true")
    public  DataSource zaozhiDataSource(DruidProperties druidProperties){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.hbonline")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.hbonline", name = "enabled", havingValue = "true")
    public  DataSource hbonlineDataSource(DruidProperties druidProperties){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource)
    {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
        setDataSource(targetDataSources, DataSourceType.SLAVE.name(), "slaveDataSource");
        setDataSource(targetDataSources, DataSourceType.OUTWATER.name(), "outwaterDataSource");
        setDataSource(targetDataSources, DataSourceType.LWJH.name(), "lwjhDataSource");
        setDataSource(targetDataSources,DataSourceType.GUOCHENG.name(),"guoChengSource");
        setDataSource(targetDataSources,DataSourceType.VOC.name(),"vocDataSource");
        setDataSource(targetDataSources,DataSourceType.YOUYAN.name(),"youyanDataSource");
        setDataSource(targetDataSources,DataSourceType.JIAYOU.name(),"jiayouDataSource");
        setDataSource(targetDataSources,DataSourceType.QIXIU.name(),"qixiuDataSource");
        setDataSource(targetDataSources,DataSourceType.JINSHU.name(),"jinshuDataSource");
        setDataSource(targetDataSources,DataSourceType.LINGXING.name(),"lingxingDataSource");
        setDataSource(targetDataSources,DataSourceType.ZAOZHI.name(),"zaozhiDataSource");
        setDataSource(targetDataSources,DataSourceType.HBONLINE.name(),"hbonlineDataSource");
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }
    
    /**
     * 设置数据源
     * 
     * @param targetDataSources 备选数据源集合
     * @param sourceName 数据源名称
     * @param beanName bean名称
     */
    public void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName)
    {
        try
        {
            DataSource dataSource = SpringUtils.getBean(beanName);
            targetDataSources.put(sourceName, dataSource);
        }
        catch (Exception e)
        {
        }
    }

    /**
     * 去除监控页面底部的广告
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
    public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties)
    {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        final String filePath = "support/http/resources/js/common.js";
        // 创建filter进行过滤
        Filter filter = new Filter()
        {
            @Override
            public void init(javax.servlet.FilterConfig filterConfig) throws ServletException
            {
            }
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException
            {
                chain.doFilter(request, response);
                // 重置缓冲区，响应头不会被重置
                response.resetBuffer();
                // 获取common.js
                String text = Utils.readFromResource(filePath);
                // 正则替换banner, 除去底部的广告信息
                text = text.replaceAll("<a.*?banner\"></a><br/>", "");
                text = text.replaceAll("powered.*?shrek.wang</a>", "");
                response.getWriter().write(text);
            }
            @Override
            public void destroy()
            {
            }
        };
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
}
