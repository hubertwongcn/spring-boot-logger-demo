package hk.gov.hd.biz.service;

import hk.gov.hd.biz.entity.User;

/**
 * @author hubertwong
 * @since 2024/8/4 12:26
 */
public interface UserService {
    /**
     * Create user
     *
     * @param user {@link User}
     * @return Create {@link User}
     */
    User createUser(User user);

    /**
     * Get user by id
     *
     * @param id {@link User} id
     * @return {@link User}
     */
    User getUserById(Long id);
}
