package com.springboot.util;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpRequest格式转换工具类 使用了FastJSON作为JSON格式的转换工具
 * 如果是form格式的请求，其请求参数是可以通过getParameterMap方法进行获取，
 * 但是application/json的请求方式，可能就需要自行转换拼接一下，
 * 最终转换成一个Map形式，当然也可以转换成JSONObject格式，对象DTO格式等等，
 */
public class HttpRequestUtil {

    /**
     * 通用请求格式转换
     * 
     * @param httpServletRequest
     * @return
     */
    public Map<String, String> commonHttpRequestParamConvert(HttpServletRequest httpServletRequest) {
        Map<String, String> params = new HashMap<>();
        try {
            Map<String, String[]> requestParams = httpServletRequest.getParameterMap();
            if (requestParams != null && !requestParams.isEmpty()) {
                requestParams.forEach((key, value) -> params.put(key, value[0]));
            } else {
                StringBuilder paramSb = new StringBuilder();
                try {
                    String str = "";
                    BufferedReader br = httpServletRequest.getReader();
                    while ((str = br.readLine()) != null) {
                        paramSb.append(str);
                    }
                } catch (Exception e) {
                    System.out.println("httpServletRequest get requestbody error, cause : " + e);
                }
                if (paramSb.length() > 0) {
                    JSONObject paramJsonObject = JSON.parseObject(paramSb.toString());
                    if (paramJsonObject != null && !paramJsonObject.isEmpty()) {
                        paramJsonObject.forEach((key, value) -> params.put(key, String.valueOf(value)));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("commonHttpRequestParamConvert error, cause : " + e);
        }
        return params;
    }
}
