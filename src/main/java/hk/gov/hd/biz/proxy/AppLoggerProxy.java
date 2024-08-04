package hk.gov.hd.biz.proxy;

import hk.gov.blt.core.logging.AppLogger;
import hk.gov.hd.biz.context.AppLoggerContext;
import hk.gov.hd.biz.factory.AppLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hubertwong
 * @since 2024/8/4 15:42
 */
@Primary
@Component
public class AppLoggerProxy implements InvocationHandler {
    @Autowired
    private AppLoggerFactory appLoggerFactory;

    public AppLogger getProxy() {
        return (AppLogger) Proxy.newProxyInstance(AppLogger.class.getClassLoader(), new Class[]{AppLogger.class}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        AppLogger logger = AppLoggerContext.getLogger();
        if (logger == null) {
            logger = appLoggerFactory.getAppLogger("default");
        }
        return method.invoke(logger, args);
    }
}
