package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.model.Member;
import com.example.teampredictionworldcup.model.UserAuthority;
import com.example.teampredictionworldcup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member user = memberRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User notfound: %s".formatted(username)));

        return new User(user.getName(), user.getPassword(), convertAuthoroties(user.getAuthority()));
    }

    private Collection<? extends GrantedAuthority> convertAuthoroties(UserAuthority userAuthority) {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_%S".formatted(userAuthority.name()))
        );
    }
}
