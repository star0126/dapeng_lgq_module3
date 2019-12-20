package com.system.mytemplate;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * @program: dapeng_lgq
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-12-14 00:10
 * @version:第1版
 * @description: 返回json字符处理
 **/

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<Data> implements Serializable {

    private int code;   //返回码 非0即失败
    private String msg; //消息提示
    private Map<Object, Object> data; //返回的数据


    public static String failed(int code, String msg,Map<Object, Object> data) {
        return  JSON.toJSONString(new JsonResult(code, msg,data));
    }

}
