package com.gk.cloud.oauth2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户信息服务
 * 实现 Spring Security的UserDetailsService接口方法，用于身份认证
 * @author gk
 * 2019年3月25日 下午1:29:21
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * 此处写死了用户名，做测试用
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		if("GaoKai".equalsIgnoreCase(userName)) {
			User user = new User(userName, passwordEncoder.encode("123456") , AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
			return user;
		}else {
			throw  new UsernameNotFoundException("用户["+userName+"]不存在");
		}
	}

}
