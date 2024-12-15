package cn.bugstack.xfg.dev.tech.domain.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthService{

    void register(String userName, String password);

    String login(String userName, String password);

}
