package hk.gov.hd.biz.listener;

import hk.gov.hd.biz.factory.AppLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Config Refresh Listener
 *
 * @author hubertwong
 * @since 2024/8/4 15:38
 */
@Component
public class ConfigRefreshListener {
    @Autowired
    private AppLoggerFactory appLoggerFactory;

    @EventListener(RefreshScopeRefreshedEvent.class)
    public void onRefresh() {
        appLoggerFactory.refreshLoggers();
    }
}
