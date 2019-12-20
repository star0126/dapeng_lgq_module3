package com.system.mytemplate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @program: dapeng_lgq
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-12-14 14:52
 * @version:第1版
 * @description: 通用异常类
 **/
@Getter
@Setter
@Accessors(chain = true)
public class GeneralException extends RuntimeException {
    private Integer code;

    public GeneralException(Integer code, String message){
        super(message);
        this.code = code;
    }


}
