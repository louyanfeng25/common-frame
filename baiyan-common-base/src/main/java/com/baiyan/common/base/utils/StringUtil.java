package com.baiyan.common.base.utils;


import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author baiyan
 * @date 2020/11/13
 */
public class StringUtil extends StrUtil {

    public static final String SPLIT_REGEX_COMMA = ",";

    public static final String REGEX_NUMERIC = "^[+-]?[0-9]*$";

    /**
     * 金额小数位校验
     */
    public static final Pattern MONEY_CHECK = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");

    /**
     * @param str
     * @return
     */
    public static List<String> splitFilterBlank(String str, String regex) {
        if (StringUtil.isBlank(str) || StringUtil.isBlank(regex)) {
            return new ArrayList<>();
        }
        String[] splits = str.split(regex);
        List<String> results = new ArrayList<>();
        for (String split : splits) {
            if (StringUtil.isBlank(split)) {
                continue;
            }
            results.add(split);
        }
        return results;
    }

    /**
     * @param str
     * @return
     */
    public static List<Long> commaSplitFilterBlankAsLongList(String str) {
        List<String> strings = splitFilterBlank(str, SPLIT_REGEX_COMMA);
        List<Long> longs = new ArrayList<>();
        for (String string : strings) {
            if (!StringUtil.isNumeric(string)) {
                throw new IllegalArgumentException("请传入正确的数字");
            }
            Long num = Long.valueOf(string);
            longs.add(num);
        }
        return longs;
    }

    public static boolean isNumeric(String string) {
        return !StringUtil.isBlank(string) && string.matches(REGEX_NUMERIC);
    }

    public static String buildUrl(String url, Map<String, String> params) {
        StringBuilder builder = new StringBuilder(url);
        for (String param : params.keySet()) {
            builder.append("&");
            builder.append(param);
            builder.append("=");
            builder.append(params.get(param));
        }
        return builder.toString();
    }

    public static String getString(Object obj, String def) {
        return Objects.isNull(obj) ? def : String.valueOf(obj);
    }

    public static List<Integer> commaSplitFilterBlankAsIntegerList(String str) {
        List<String> strings = splitFilterBlank(str, SPLIT_REGEX_COMMA);
        List<Integer> ints = new ArrayList<>();
        for (String string : strings) {
            if (!StringUtil.isNumeric(string)) {
                throw new IllegalArgumentException("请传入正确的数字");
            }
            Integer num = Integer.valueOf(string);
            ints.add(num);
        }
        return ints;
    }

    /**
     * 根据文件路径截取文件名
     */
    public static String getFileNameByPath(String path) {
        if (StringUtil.isBlank(path)) {
            return null;
        }
        return path.substring(path.lastIndexOf("/") + 1);
    }

    /**
     * 获取后缀
     */
    public static String getSuffix(String path) {
        if (StringUtil.isBlank(path)) {
            return null;
        }
        return path.substring(path.lastIndexOf("."));
    }

    /**
     * 拼接多个字符
     *
     * @author yezhangle
     * @date 2018/3/26.
     */
    public static String append(String... targets) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String target : targets) {
            if (StringUtil.isNotBlank(target)) {
                stringBuffer.append(target);
            }
        }
        return stringBuffer.toString();
    }

    public static Integer getInteger(String value) {
        if (isBlank(value)) {
            return null;
        }
        return Integer.valueOf(value);
    }

    public static Double getDouble(String value) {
        if (isBlank(value)) {
            return null;
        }
        return Double.valueOf(value);
    }

    public static String emptyString(String s) {
        if (StringUtil.isEmpty(s)) {
            return "未知";
        }
        return s;
    }

    /**
     * 金额小数位校验
     * 允许0位，1位，2位小数
     * @param money
     * @return 是否满足校验规则
     */
    public static boolean moneyCheck(String money) {
        if(StringUtil.isBlank(money)){
            return Boolean.FALSE;
        }
        if (StringUtil.MONEY_CHECK.matcher(money).matches()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
