package cn.bugstack.xfg.dev.tech.domain.auth.service;

import cn.bugstack.xfg.dev.tech.domain.auth.model.entity.UserEntity;
import cn.bugstack.xfg.dev.tech.domain.auth.model.valobj.RoleTypeEnum;
import cn.hutool.jwt.JWT;
import com.google.common.cache.Cache;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private Cache<String, UserEntity> userCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void register(String userName, String password) {

        UserEntity userEntity = UserEntity.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .roles(Arrays.asList(RoleTypeEnum.USER, RoleTypeEnum.ADMIN))
                .build();

        userCache.put(userName, userEntity);
    }

    @Override
    public String login(String userName, String password) {
        // 登录验证
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        // 验证通过，获取 token
        String token = JWT.create()
                .setExpiresAt(new Date(System.currentTimeMillis() + (1000 * 30)))
                .setPayload("username", userName)
                .setKey("key".getBytes(StandardCharsets.UTF_8))
                .sign();

        return token;
    }

}
