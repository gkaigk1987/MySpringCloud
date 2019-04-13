package com.gk.cloud.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 权限验证过滤器
 * @author gk
 * 2019年4月13日 下午3:15:49
 */
@Component
public class AccessFilter extends ZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	@Override
	public boolean shouldFilter() {
		//直接返回true表示所有都需要过滤
		//需要权限校验URL
//		RequestContext currentContext = RequestContext.getCurrentContext();
//		HttpServletRequest request = currentContext.getRequest();
//        if ("/apigateway/order/api/v1/order/save".equalsIgnoreCase(request.getRequestURI())) {
//            return true;
//        } else if ("/apigateway/order/api/v1/order/list".equalsIgnoreCase(request.getRequestURI())) {
//            return true;
//        } else if ("/apigateway/order/api/v1/order/find".equalsIgnoreCase(request.getRequestURI())) {
//            return true;
//        }
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		//以下代码判断是否有权限访问相应资源
		logger.info("Request URL is : {}", request.getRequestURL().toString());
		String authorization = request.getHeader("Authorization");
		if(StringUtils.isEmpty(authorization)) {
			//此处应该还需要增加authorization合法性的判断
			logger.warn("Authorization token is empty");
			currentContext.setSendZuulResponse(false);	//返回，不路由url
			currentContext.setResponseStatusCode(401);
			currentContext.setResponseBody("Authorization token is empty");
            return null;
		}
		logger.info("Authorization token is right");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
