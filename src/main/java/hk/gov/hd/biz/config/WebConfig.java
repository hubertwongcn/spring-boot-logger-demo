package hk.gov.hd.biz.config;

import hk.gov.hd.biz.interceptor.AppLoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hubertwong
 * @since 2024/8/4 16:13
 */
@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AppLoggerInterceptor appLoggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appLoggerInterceptor);
    }
}
