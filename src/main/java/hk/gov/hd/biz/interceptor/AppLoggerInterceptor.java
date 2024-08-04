package hk.gov.hd.biz.interceptor;

import hk.gov.blt.core.logging.AppLogger;
import hk.gov.hd.biz.context.AppLoggerContext;
import hk.gov.hd.biz.factory.AppLoggerFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author hubertwong
 * @since 2024/8/4 15:46
 */
@Component
public class AppLoggerInterceptor implements HandlerInterceptor {
    @Autowired
    private AppLoggerFactory appLoggerFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String configName = request.getHeader("App-Config-Name");
        if  (configName != null) {
            AppLogger logger = appLoggerFactory.getAppLogger(configName);
            AppLoggerContext.setLogger(logger);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AppLoggerContext.clear();
    }
}
