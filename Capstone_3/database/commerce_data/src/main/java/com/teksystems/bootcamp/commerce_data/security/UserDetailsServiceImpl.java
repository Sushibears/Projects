package com.teksystems.bootcamp.commerce_data.security;

import java.util.ArrayList;
import java.util.List;

import com.teksystems.bootcamp.commerce_data.models.Authority;
import com.teksystems.bootcamp.commerce_data.models.LoginUser;
import com.teksystems.bootcamp.commerce_data.repository.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    LoginUserRepository loginUserRepository;

    @Override
    public UserDetails loadUserByUsername(String user_username) throws UsernameNotFoundException {
        LoginUser appLoginUser = loginUserRepository.findByUsername(user_username).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

        List grantList = new ArrayList();
        for (Authority authority : appLoginUser.getAuthority()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
            grantList.add(grantedAuthority);
        }

        UserDetails user = (UserDetails) new User(appLoginUser.getUsername(), appLoginUser.getPassword(), grantList);
        return user;
    }
}
