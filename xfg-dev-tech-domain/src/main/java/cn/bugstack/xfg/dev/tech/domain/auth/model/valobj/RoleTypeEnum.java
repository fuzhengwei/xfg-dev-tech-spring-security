package cn.bugstack.xfg.dev.tech.domain.auth.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RoleTypeEnum {

    ADMIN("ADMIN", "管理员"),
    USER("USER", "用户"),
    ;

    private String code;
    private String desc;

}
