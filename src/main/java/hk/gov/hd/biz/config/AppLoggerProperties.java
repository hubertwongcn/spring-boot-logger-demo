package hk.gov.hd.biz.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hubertwong
 * @since 2024/8/4 11:40
 */
@Setter
@Getter
@SpringBootConfiguration
@ConfigurationProperties(prefix = "applogger")
@Validated
//@RefreshScope
public class AppLoggerProperties {
    @NotEmpty
    private Map<String, LoggerConfig> configs = new ConcurrentHashMap<>();

    @Validated
    public static class LoggerConfig {
        @NotNull
        private String level;
        @NotNull
        private String format;

        public @NotNull String getLevel() {
            return level;
        }

        public void setLevel(@NotNull String level) {
            this.level = level;
        }

        public @NotNull String getFormat() {
            return format;
        }

        public void setFormat(@NotNull String format) {
            this.format = format;
        }
    }
}
