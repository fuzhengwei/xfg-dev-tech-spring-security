package cn.bugstack.xfg.dev.tech.trigger.http;

import cn.bugstack.xfg.dev.tech.domain.auth.service.IAuthService;
import cn.bugstack.xfg.dev.tech.trigger.http.response.Response;
import cn.hutool.jwt.JWT;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Resource
    private IAuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("query_user_name")
    public Response<String> queryUserName() {
        try {
            // 获取当前认证的用户信息
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            return Response.<String>builder()
                    .code(Response.ResponseCode.SUCCESS.getCode())
                    .info(Response.ResponseCode.SUCCESS.getInfo())
                    .data(principal.toString())
                    .build();
        } catch (Exception e) {
            return Response.<String>builder()
                    .code(Response.ResponseCode.UN_ERROR.getCode())
                    .info(Response.ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @PostMapping("register")
    public Response<Boolean> register(@RequestParam String userName, @RequestParam String password) {
        try {
            log.info("注册用户:{}", userName);
            authService.register(userName, password);
            return Response.<Boolean>builder()
                    .code(Response.ResponseCode.SUCCESS.getCode())
                    .info(Response.ResponseCode.SUCCESS.getInfo())
                    .data(true)
                    .build();
        } catch (Exception e) {
            log.info("注册用户失败:{}", userName);
            return Response.<Boolean>builder()
                    .code(Response.ResponseCode.UN_ERROR.getCode())
                    .info(Response.ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @PostMapping("login")
    public Response<String> login(@RequestParam String userName, @RequestParam String password) {
        try {
            log.info("登录用户:{}", userName);
            // 登录获取 token
            String token = authService.login(userName, password);

            return Response.<String>builder()
                    .code(Response.ResponseCode.SUCCESS.getCode())
                    .info(Response.ResponseCode.SUCCESS.getInfo())
                    .data(token)
                    .build();
        } catch (Exception e) {
            log.info("登录用户失败:{}", userName);
            return Response.<String>builder()
                    .code(Response.ResponseCode.UN_ERROR.getCode())
                    .info(Response.ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

}
