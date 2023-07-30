package com.hiyongz.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 10287
 */
@Data
public class UserVO {

    @ApiModelProperty("主键")
    private Integer id; //ID
    @ApiModelProperty("用户名")
    private String username; //用户名
    private String name; //姓名
    private Short gender; //性别 , 1 男, 2 女
    private String avatar; //头像
    private Integer deptId; //部门ID
    private String role; //职位
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
