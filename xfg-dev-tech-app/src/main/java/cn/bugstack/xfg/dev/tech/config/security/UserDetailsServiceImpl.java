package cn.bugstack.xfg.dev.tech.config.security;

import cn.bugstack.xfg.dev.tech.domain.auth.model.entity.UserEntity;
import com.google.common.cache.Cache;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private Cache<String, UserEntity> userCache;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userCache.getIfPresent(username);
        if (null == userEntity) return null;
        return new UserDetailAuthSecurity(userEntity);
    }

}
