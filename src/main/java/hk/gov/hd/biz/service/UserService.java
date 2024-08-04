package hk.gov.hd.biz.service;

import hk.gov.hd.biz.entity.User;

/**
 * @author hubertwong
 * @since 2024/8/4 12:26
 */
public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
}
