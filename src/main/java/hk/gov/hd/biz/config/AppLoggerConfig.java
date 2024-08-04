package hk.gov.hd.biz.config;

import hk.gov.blt.core.logging.AppLogger;
import hk.gov.hd.biz.proxy.AppLoggerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author hubertwong
 * @since 2024/8/4 15:48
 */
@SpringBootConfiguration
public class AppLoggerConfig {
    @Autowired
    private AppLoggerProxy appLoggerProxy;

    @Bean
    @Primary
    public AppLogger appLogger() {
        return appLoggerProxy.getProxy();
    }
}
