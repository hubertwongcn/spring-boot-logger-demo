package hk.gov.hd.biz.context;

import hk.gov.blt.core.logging.AppLogger;

/**
 * @author hubertwong
 * @since 2024/8/4 15:40
 */
public class AppLoggerContext {
    private static final ThreadLocal<AppLogger> LOGGER_CONTEXT = new ThreadLocal<>();

    public static void setLogger(AppLogger logger) {
        LOGGER_CONTEXT.set(logger);
    }

    public static AppLogger getLogger() {
        return LOGGER_CONTEXT.get();
    }

    public static void clear() {
        LOGGER_CONTEXT.remove();
    }
}
