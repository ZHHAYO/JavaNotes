package com.hiyongz.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // getter方法、setter方法、toString方法、hashCode方法、equals方法
@NoArgsConstructor //无参构造
@AllArgsConstructor //全参构造
@ApiModel(value = "用户实体类", description = "存放用户表字段")
@TableName("user")
public class User {
    @ApiModelProperty("主键")
    private Integer id; //ID

    @ApiModelProperty("用户名")
    private String username; //用户名
    @ApiModelProperty("密码")
    private String password; //密码
    private String name; //姓名
    private Short gender; //性别 , 1 男, 2 女
    private String avatar; //头像
    private String phone; //手机号
    private String email; //邮箱
    private Integer deptId; //部门ID
    private String role; //职位
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
