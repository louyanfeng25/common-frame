package com.baiyan.common.base.constant;

/**
 * 正则常量
 * 
 * @author baiyan
 * @time 2020/11/23 19:14
 */
public class RegexpConstant {

    /**
     * 18位身份证校验
     */
    public final static String ID_CARD="^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";

    /**
     * 纯数字校验
     */
    public final static String NUMBER="^[1-9]\\d+$|^[0-9]$";

    /**
     * 用户名
     */
    public final static String REGEXP_USERNAME = "^[a-zA-Z][a-zA-Z0-9_]{3,19}$";

    /**
     * 真实用户名
     */
    public final static String REGEXP_REAL_NAME = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";

    /**
     * 密码
     */
    public final static String USER_PWD = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~!@#$%^&*()_+=])[\\da-zA-Z~!@#$%^&*()_+=]{8,16}$";

    /**
     * 手机号
     */
    public final static String MOBILE_PHONE = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

    /**
     * ip校验
     */
    public final static String REGEXP_IP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    /**
     * ipv6校验，同时支持ipv4及ipv4兼容地址和映像地址
     */
    public final static String REGEXP_IP_V6 = "^\\s*$|^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}||:))|(([0-9A-Fa-f]{1,4}:){5}((:[0-9A-Fa-f]{1,4}){2}|:))|(([0-9A-Fa-f]{1,4}:){4}((:[0-9A-Fa-f]{1,4}){3}|:))|(([0-9A-Fa-f]{1,4}:){3}((:[0-9A-Fa-f]{1,4}){4}|:))|(([0-9A-Fa-f]{1,4}:){2}((:[0-9A-Fa-f]{1,4}){5}|:))|(([0-9A-Fa-f]{1,4}:){1}((:[0-9A-Fa-f]{1,4}){6}|:))|(:(:[0-9A-Fa-f]{1,4}){1,7})|((::)?((25[0-5]|2[0-4]\\d|[01]?\\d\\d?).){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)))\\s*$";

    /**
     * 非必填ip校验
     */
    public final static String REGEXP_IP_NULL = "^$|^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    /**
     * ip段校验
     */
    public final static String REGEXP_IP_SEGMENT = "^(([0-9a-f]{1,4}:){7}[0-9a-f]{1,4})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(::([0-9a-f]{1,4}:){0,6}[0-9a-f]{1,4})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){1,7}:)(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^([0-9a-f]{1,4}:(:[0-9a-f]{1,4}){1,6})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){2}(:[0-9a-f]{1,4}){1,5})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){3}(:[0-9a-f]{1,4}){1,4})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){4}(:[0-9a-f]{1,4}){1,3})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){5}(:[0-9a-f]{1,4}){1,2})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^(([0-9a-f]{1,4}:){6}:[0-9a-f]{1,4})(\\/(\\d{1,2}|1[0-1]\\d|12[0-8]))?$|^::FFFF:(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{2}|\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{2}|\\d)){3}(\\/(\\d|[12]\\d|3[0-2]))?$|^(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{2}|\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{2}|\\d)){3}(\\/(\\d|[12]\\d|3[0-2]))?$";

    /**
     * url校验(必须带http或https)
     */
    public final static String REGEXP_URL = "^((https?|ftp):\\/\\/)(((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:)*@)?(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))|((([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?)(:\\d*)?)(\\/((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)+(\\/(([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)*)*)?)?(\\?((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|[\\uE000-\\uF8FF]|\\/|\\?)*)?(\\#((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|\\/|\\?)*)?$";

    /**
     * url校验(http和https可有可无）
     */
    public final static String REGEXP_URL_TWO = "^((https?|ftp):\\/\\/)?(((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:)*@)?(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))|((([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?)(:\\d*)?)(\\/((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)+(\\/(([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)*)*)?)?(\\?((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|[\\uE000-\\uF8FF]|\\/|\\?)*)?(\\#((([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|\\/|\\?)*)?$";

    /**
     * 域名校验
     */
    public final static String REGEXP_DOMAIN = "(?i)^([a-zA-Z0-9_-]+\\.)+[a-zA-Z0-9_-]+$";

    /**
     * 特殊字符校验
     */
    public final static String REGEXP_SPECIAL_CHAR = "^[^(\\&)(\\<)(\\>)(\\\\)]*$";

    /**
     * 手机
     */
    public final static String REGEXP_MOBILE = "^$|^1(3|4|5|7|8)[0-9]{9}$";

    /**
     * 固定电话
     */
    public final static String REGEXP_PHONE = "^((0\\d{2,3})-?)(\\d{7,8})(-?(\\d{0,4}))?$";

    /**
     * 非必填固定电话
     */
    public final static String REGEXP_TEL = "^$|^((0\\d{2,3})-?)(\\d{7,8})(-?(\\d{0,4}))?$";

    /**
     * 联系电话(手机or座机)
     */
    public final static String REGEXP_MOBILE_OR_PHONE = "^$|^(1(3|4|5|7|8)[0-9]{9})$|^((0\\d{2,3})-?)(\\d{7,8})(-?(\\d{0,4}))?$";

    /**
     * 端口
     */
    public final static String REGEXP_PORT = "^6(?:(553[0-5])|(55[0-2]\\d)|(5[0-4]\\d{2})|([0-4]\\d{3}))$|^([1-5]?\\d{1,4})$";

    /**
     * 传真
     */
    public final static String REGEXP_FAX = "^(\\d{3,4})?[-]?\\d{7,8}$";

    /**
     * 邮箱
     */
    public final static String REGEXP_EMAIL = "^$|(?i)^[a-z0-9]+[-._a-z0-9]*@([a-z0-9]+[-a-z0-9]*\\.){1,63}[a-z0-9]+$";

    /**
     * 经度
     */
    public final static String REGEXP_LONGITUDE = "^$|^(\\-|\\+)?((\\d|[1-9]\\d|1[0-7]\\d|0{1,3})\\.\\d{1,6}|(\\d|[1-9]\\d|1[0-7]\\d|0{1,3}|180)\\.0{1,6})$";

    /**
     * 纬度
     */
    public final static String REGEXP_LATITUDE = "^$|^[\\-|\\+]?([0-8]?\\d{1}\\.\\d{1,6}|90\\.0{1,6})$";

    /**
     * 正整数
     */
    public final static String REGEXP_POSITIVE_INTEGERS = "^\\d+$";

    /**
     * 中文
     */
    public final static String REGEXP_CHINESE = "^[\\u4e00-\\u9fa5]{0,}$";

    /**
     * 排除注入风险
     */
    public final static String REGEXP_SAFE = "^[^<>&\\\\]*$";

    /**
     * 时间格式正则
     */
    public final static String REGEXP_DATE_TIME = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";

}

