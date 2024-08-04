package hk.gov.hd.biz.cache;

import hk.gov.blt.core.logging.AppLogger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @author hubertwong
 * @since 2024/8/4 11:58
 */
@Component
public class AppLoggerCache {
    private final Map<String, AppLogger> cache = new ConcurrentHashMap<>();

    public AppLogger get(String configName, Supplier<AppLogger> creator) {
        return cache.computeIfAbsent(configName, k -> creator.get());
    }

    /**
     * 清理长时间未使用的 logger<p>
     * 每小时执行一次
     */
    @Scheduled(fixedRate = 3600000)
    public void cleanup() {
        // 这里可以添加清理逻辑，例如移除长时间未使用的 logger
        cache.entrySet().removeIf(entry -> {
            // 检查 logger 是否长时间未使用
            // 这里需要在 AppLogger 中添加最后使用时间的跟踪
            return isExpired(entry.getValue());
        });
    }

    private boolean isExpired(AppLogger logger) {
        // TODO 实现检查 logger 是否过期的逻辑
        // for example: 如果 logger 超过 24 小时未使用，则认为它已过期
        return false; // 此处根据实际情况实现
    }
}
