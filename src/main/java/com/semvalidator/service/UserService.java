package com.semvalidator.service;

import com.semvalidator.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author Created by comp-dev on 6/11/17.
 */
public interface UserService extends GenericService<User>, UserDetailsService {
    User findByLogin(String login);
}
