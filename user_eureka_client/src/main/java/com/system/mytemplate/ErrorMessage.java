package com.system.mytemplate;

/**
 * @ProjectName: dapeng_lgq
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/13 16:19
 * @version: 1.0
 * @description: 错误信息枚举类
 **/
public enum  ErrorMessage {
    //The login parameter is empty
    LOGINPARAMETERNUL("登录时参数为空！"),
    NAMENUL("用户名为空！"),
    PWDNUL("密码为空！"),
    REGISTERPARAMETERNUL("注册时参数为空！");

    private String text;

    private ErrorMessage(String desc) {
        text = desc;
    }

    public static ErrorMessage getInstance(String status) {
        ErrorMessage[] allStatus = ErrorMessage.values();
        for (ErrorMessage ws : allStatus) {
            if (ws.getText().equalsIgnoreCase(status)) {
                return ws;
            }
        }
        throw new IllegalArgumentException("status值非法，没有符合类型的枚举对象");
    }

    public String getText() {
        return text;
    }
}
