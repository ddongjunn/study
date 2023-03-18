package com.djlee.jwt.config.auth;

import com.djlee.jwt.model.User;
import com.djlee.jwt.repository.UserRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalService implements UserDetailsService {

    private final UserRepositoy userRepositoy;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepositoy.findByUsername(username);
        return new PrincipalDetails(userEntity);
    }
}
