package com.gk.cloud.zuul.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class RequestFilter extends ZuulFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);
	
	private String printArray(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

	/**
	 * 	配置是否需要过滤：true：需要，false：不需要
     * @return
     */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String contentType = request.getHeader("Content-Type");
		System.out.println("================"+ contentType +"================");
		Map<String, String[]> parameterMap = request.getParameterMap();
		if(null == parameterMap || parameterMap.size() < 1) {
			if(contentType.indexOf("multipart/form-data") >= 0) {
				//二进制类型传递参数，可上传副本
				logger.info("当前content-type为:{},无法获取参数",contentType);
			}else {
				//JSON格式
				try {
					String reqBody = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
					logger.info("reqBody: {}",reqBody);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			//非JSON格式
			Map<String,String> params = new HashMap<>();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                params.put(entry.getKey(),printArray(entry.getValue()));
            }
            logger.info("parameters:{}",JSONObject.toJSONString(parameterMap));
		}
		
		return null;
	}

	/**
	 * 	配置过滤类型，有四种不同生命周期的过滤器类型
     * 1. pre：路由之前
     * 2. routing：路由之时
     * 3. post：路由之后
     * 4. error：发送错误调用
     * @return
     */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 	配置过滤器顺序
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

}
