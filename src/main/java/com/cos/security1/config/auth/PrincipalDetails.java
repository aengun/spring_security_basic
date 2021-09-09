package com.cos.security1.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다
// 로그인 진행이 완료되면 시큐리티 session을 만들어준다. (Security ContextHolder)
// ContextHolder에 들어오는 오브젝트의 타입 : Authentication 타입 객체
// Authentication 안에 User정보가 있어야 하는데,,
// User오브젝트 타입은 UserDetails 타입 객체여야 한다.

// Security Session => Authentication => UserDetails

import com.cos.security1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user; //콤포지션

    public PrincipalDetails(User user){
        this.user = user;
    }

    @Override //해당 유저의 권한을 리턴하는 곳
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        user.getRole(); //타입 불일치,, 다음과 같이 만든다.
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override //계정 만료?
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //계정 잠김?
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //계정 비밀번호 1년 지남?
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //계정 활성화?
    public boolean isEnabled() {

        //우리 사이트 : 1년동안 회원이 로그인을 안 하면, 휴면계정으로 하기로 함
        //현재시간 - 로그인 시간 => 1년을 초과하면 return false 이런 식으로 구현하면 됨
//        user.getLoginDate();

        return true;
    }
}
