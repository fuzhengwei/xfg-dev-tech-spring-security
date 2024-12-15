package cn.bugstack.xfg.dev.tech.trigger.http;

import cn.bugstack.xfg.dev.tech.trigger.http.dto.CreatePayRequestDTO;
import cn.bugstack.xfg.dev.tech.trigger.http.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/mall/")
public class MallController {

    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "create_pay_order", method = RequestMethod.POST)
    public Response<String> createPayOrder(@RequestBody CreatePayRequestDTO createPayRequestDTO) {
        try {
            // 获取当前认证的用户信息
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();

            String userName = (String) principal;
            String productId = createPayRequestDTO.getProductId();

            log.info("商品下单，根据商品ID创建支付单开始 userName:{} productId:{}", userName, productId);

            return Response.<String>builder()
                    .code(Response.ResponseCode.SUCCESS.getCode())
                    .info(Response.ResponseCode.SUCCESS.getInfo())
                    .data(userName + " 下单成功。单号：" + RandomStringUtils.randomAlphabetic(12))
                    .build();
        } catch (Exception e) {
            log.error("商品下单，根据商品ID创建支付单开始 productId:{}", createPayRequestDTO.getProductId(), e);
            return Response.<String>builder()
                    .code(Response.ResponseCode.UN_ERROR.getCode())
                    .info(Response.ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

}
