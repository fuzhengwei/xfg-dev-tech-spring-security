package cn.bugstack.xfg.dev.tech.config;

import cn.bugstack.xfg.dev.tech.domain.auth.model.entity.UserEntity;
import cn.bugstack.xfg.dev.tech.domain.auth.model.valobj.RoleTypeEnum;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
//@Configuration
public class GuavaConfig {

    @Bean(name = "userCache")
    public Cache<String, UserEntity> userCache(PasswordEncoder passwordEncoder) {
        Cache<String, UserEntity> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(365, TimeUnit.DAYS)
                .build();

        UserEntity userEntity01 = UserEntity.builder()
                .userName("xiaofuge")
                .password(passwordEncoder.encode("123456"))
                .roles(Arrays.asList(RoleTypeEnum.ADMIN))
                .build();

        UserEntity userEntity02 = UserEntity.builder()
                .userName("liergou")
                .password(passwordEncoder.encode("123456"))
                .roles(Arrays.asList(RoleTypeEnum.USER))
                .build();

        log.info("测试账密01 xiaofuge/123456 权限；admin");
        log.info("测试账密02 liergou/123456 权限；user");

        cache.put(userEntity01.getUserName(), userEntity01);
        cache.put(userEntity02.getUserName(), userEntity02);
        return cache;
    }

}
