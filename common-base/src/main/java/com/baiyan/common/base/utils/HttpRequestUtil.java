package com.baiyan.common.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * http请求工具类
 *
 * @author baihan
 * @date 2020/11/13
 */
@Slf4j
public class HttpRequestUtil {

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ra.getRequest();
        return request;
    }

    /**
     * 获取response
     * @return
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ra.getResponse();
        return response;
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取客户端IP
     */
    public static String getIpAddr() {
        return getIpAddr(getRequest());
    }

    /**
     * 获取图片hashcode
     * @return
     */
    public static String getImageCodeHash() {
        return getRequest().getHeader("Hash");
    }


    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            String[] localIPs = new String[]{"0:0:0:0:0:0:0:1", "127.0.0.1"};
            List<String> localIPList = Arrays.asList(localIPs);
            if (localIPList.contains(ip)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ip = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    log.error("根据网卡获取本机配置的IP出错");
                }
            }
        }
        int ipLen = 15;
        if (StringUtils.isNotBlank(ip) && ip.length() > ipLen) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            } else {
                ip = ip.substring(0, 16);
            }
        }
        return ip;
    }
}
