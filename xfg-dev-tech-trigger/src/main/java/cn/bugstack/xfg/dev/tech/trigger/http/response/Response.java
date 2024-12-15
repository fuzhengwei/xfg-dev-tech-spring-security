package cn.bugstack.xfg.dev.tech.trigger.http.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 7000723935764546321L;

    private String code;
    private String info;
    private T data;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public enum ResponseCode {
        DENIED("-1","没有访问该资源的权限"),
        SUCCESS("0000", "调用成功"),
        UN_ERROR("0001", "调用失败"),
        ILLEGAL_PARAMETER("0002", "非法参数"),
        NO_LOGIN("0003", "未登录"),
        ;

        private String code;
        private String info;

    }

}
