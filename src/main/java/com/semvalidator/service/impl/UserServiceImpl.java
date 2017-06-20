package com.semvalidator.service.impl;

import com.semvalidator.model.User;
import com.semvalidator.repository.UserRepository;
import com.semvalidator.service.UserService;
import com.semvalidator.util.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Created by comp-dev on 6/11/17.
 */

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User "+username+" not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user.getAuthorities() != null){
            for(String authority : user.getAuthorities()){
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return new SecurityUser(user.getId(), user.getLogin(), user.getPassword(), user.getProfile(), authorities);
    }

    public User save(User user, MultipartFile modelFile){
        return userRepository.saveAndFlush(user);
    }

    public User update(User user){
        User savedUser = userRepository.findOne(user.getId());
        return userRepository.saveAndFlush(savedUser);
    }

    @Transactional(readOnly = true)
    public User findById(Integer id){
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void delete(Integer id){
        userRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Page<User> findAllPageable(org.springframework.data.domain.Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
