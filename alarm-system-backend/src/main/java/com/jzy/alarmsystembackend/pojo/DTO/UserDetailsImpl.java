package com.jzy.alarmsystembackend.pojo.DTO;

import com.jzy.alarmsystembackend.pojo.DO.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 是否没有被锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 授权是否过期了
        return true;
    }

    @Override
    public boolean isEnabled() { // 这个用户是否被启用了
        return true;
    }
}
