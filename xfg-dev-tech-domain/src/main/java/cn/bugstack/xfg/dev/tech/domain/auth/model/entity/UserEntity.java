package cn.bugstack.xfg.dev.tech.domain.auth.model.entity;

import cn.bugstack.xfg.dev.tech.domain.auth.model.valobj.RoleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private String userName;
    private String password;

    private List<RoleTypeEnum> roles;

}
