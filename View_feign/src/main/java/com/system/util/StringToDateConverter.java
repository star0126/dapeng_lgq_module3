package com.system.util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: dapeng_lgq
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/11 9:54
 * @version: 1.0
 * @description: 解决日期传参问题的工具类
 **/
public class StringToDateConverter implements Converter<String, Date> {

    private static final String dateFormat = "yyyy-MM-dd";
    //private static final String dateFormat = "yyyy-MM-dd";
    private static final String shortDateFormat = "yyyy-MM-dd";
    private static final String dateFormat2 = "yyyy/MM/dd";
    //private static final String dateFormat2 = "yyyy/MM/dd";
    private static final String shortDateFormat2 = "yyyy/MM/dd";

    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();
        try {
            SimpleDateFormat formatter;
            if (source.contains("-")) {
                if (source.contains(":")) {
                    formatter = new SimpleDateFormat(dateFormat);
                } else {
                    formatter = new SimpleDateFormat(shortDateFormat);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            } else if (source.contains("/")) {
                if (source.contains(":")) {
                    formatter = new SimpleDateFormat(dateFormat2);
                } else {
                    formatter = new SimpleDateFormat(shortDateFormat2);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }

        throw new RuntimeException(String.format("parser %s to Date fail", source));

    }
}
