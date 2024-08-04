package hk.gov.hd.biz.aspect;

import hk.gov.blt.core.logging.AppLogger;
import hk.gov.hd.biz.context.AppLoggerContext;
import hk.gov.hd.biz.factory.AppLoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 主要用于处理非HTTP request的日志
 *
 * @author hubertwong
 * @since 2024/8/4 16:20
 */
@Aspect
@Component
public class AppLoggerAspect {
    @Autowired
    private AppLoggerFactory appLoggerFactory;

    /**
     * 所有MQ消费和Scheduler任务都要求配置在对应的包里面，根据包名做切面配置{@link AppLoggerAspect}
     *
     * @param joinPoint 切入点
     */
    @Around("execution(* hk.gov.hd.biz.mq..*(..)) || execution(* hk.gov.hd.biz.scheduler..*(..))")
    public Object aroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String configName = determineConfigNameForMQListener(joinPoint);
        return executeWithLogger(joinPoint, configName);
    }

    private Object executeWithLogger(ProceedingJoinPoint joinPoint, String configName) throws Throwable {
        AppLogger logger = appLoggerFactory.getAppLogger(configName);
        AppLoggerContext.setLogger(logger);
        try {
            return joinPoint.proceed();
        } finally {
            AppLoggerContext.clear();
        }
    }

    private String determineConfigNameForScheduledTask(ProceedingJoinPoint joinPoint) {
        // TODO 根据定时任务的特定参数确定 configName
        return "defaultScheduledConfig";
    }

    private String determineConfigNameForMQListener(ProceedingJoinPoint joinPoint) {
        // TODO 根据 MQ 消息的特定参数确定 configName
        return "defaultRabbitConfig";
    }
}
