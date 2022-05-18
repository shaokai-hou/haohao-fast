package com.haohao.fast.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haohao.fast.common.constant.StatusConstant;
import com.haohao.fast.domain.SysUserEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * @author haohao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    /**
     * token唯一标识
     */
    private String uuid;

    /**
     * 用户Info
     */
    private SysUserEntity sysUserEntity;

    /**
     * 用户角色列表
     */
    private Set<String> roles;

    /**
     * 用户权限列表
     */
    private Set<String> permissions;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return sysUserEntity.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return sysUserEntity.getUsername();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return Objects.equals(sysUserEntity.getState(), StatusConstant.ENABLE);
    }
}
