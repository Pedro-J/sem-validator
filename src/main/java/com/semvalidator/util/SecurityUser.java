package com.semvalidator.util;

import com.semvalidator.enums.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUser extends User {

    private Integer id;

    private UserProfile profile;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityUser(Integer id, String username, String password, UserProfile profile, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.profile = profile;
    }

    public Integer getId() {
        return id;
    }

    public UserProfile getProfile() {
        return profile;
    }
}
