package com.hiyongz.dao.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 10287
 */
@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor //全参构造
@TableName("user")

public class Userplus {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id; //ID
    private String username; //用户名
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

