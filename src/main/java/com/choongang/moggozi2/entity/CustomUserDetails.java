package com.choongang.moggozi2.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date; // Date import 추가

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {
	
	private User user;
	private Date loginTime; // 로그인 시간 추가

	public CustomUserDetails(User userData, String usernick, Date loginTime) {
		this.user = userData;
		this.loginTime = loginTime; // 로그인 시간 초기화
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
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
	
	public String getUsernick() {
		return user.getUsernick();
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
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Date getLoginTime() {
        return loginTime;
    }

}
