package cn.bugstack.xfg.dev.tech.config.security;

import cn.bugstack.xfg.dev.tech.trigger.http.response.Response;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AppAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("access error", accessDeniedException);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        response.getWriter().println(JSON.toJSONString(Response.<String>builder()
                .code(Response.ResponseCode.DENIED.getCode())
                .info(Response.ResponseCode.DENIED.getInfo())
                .build()));

        response.getWriter().flush();
    }

}
