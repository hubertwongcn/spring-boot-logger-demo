package hk.gov.hd.biz.factory;

import hk.gov.blt.core.logging.AppLogger;
import hk.gov.blt.core.logging.impl.BltAppLogger;
import hk.gov.hd.biz.cache.AppLoggerCache;
import hk.gov.hd.biz.config.AppLoggerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author hubertwong
 * @since 2024/8/4 11:56
 */
@Slf4j
@Component
@RefreshScope
public class AppLoggerFactory {
    @Autowired
    private AppLoggerProperties appLoggerProperties;

    @Autowired
    private AppLoggerCache appLoggerCache;

    public AppLogger getAppLogger(String configName) {
        return appLoggerCache.get(configName, () -> this.createAppLogger(configName));
    }

    private AppLogger createAppLogger(String configName) {
        AppLoggerProperties.LoggerConfig config = appLoggerProperties.getConfigs().get(configName);
        config = Objects.nonNull(config) ? config : appLoggerProperties.getConfigs().get("default");
        return BltAppLogger.getAppLoggerFromProp(config.getLevel(), config.getFormat());
    }

    public void refreshLoggers() {
        // TODO clean and reload?
        appLoggerCache.cleanup();
    }
}
