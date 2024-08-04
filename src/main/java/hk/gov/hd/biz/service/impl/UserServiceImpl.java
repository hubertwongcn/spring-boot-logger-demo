package hk.gov.hd.biz.service.impl;

import hk.gov.blt.core.logging.AppLogger;
import hk.gov.hd.biz.entity.User;
import hk.gov.hd.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author hubertwong
 * @since 2024/8/4 12:27
 */
@Service
public class UserServiceImpl implements UserService {
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Autowired
    private AppLogger appLogger;

    @Override
    public User createUser(User user) {
        long id = idGenerator.incrementAndGet();
        user.setId(id);
        users.put(id, user);
        appLogger.info("create user: " + user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = users.get(id);
        if (user == null) {
            appLogger.error("user not found: " + id);
        } else
            appLogger.info("get user: " + user);
        return user;
    }
}
