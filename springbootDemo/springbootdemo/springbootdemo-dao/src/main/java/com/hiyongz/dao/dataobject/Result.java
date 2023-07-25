package com.hiyongz.dao.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    // key为错误码,value为错误信息
    private static Map<Integer, String> codeMap = new HashMap<>();

    static {
        codeMap.put(1, "success");
        codeMap.put(500, "失败");
        codeMap.put(40001, "订单不存在");
        codeMap.put(40002, "库存不足");
        // ...其他错误码
    }
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    public static Result success(){
        return new Result(1,"success",null);
    }
    //查询 成功响应
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(0,msg,null);
    }

    //失败响应
    public static Result error2(Integer code){
        String msg = codeMap.get(code);
        return new Result(code,msg,null);
    }

}
