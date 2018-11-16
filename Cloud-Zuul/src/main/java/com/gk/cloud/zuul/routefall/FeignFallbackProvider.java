package com.gk.cloud.zuul.routefall;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 路由到cloud-client-feign失败后的回调
 * 
 * @author Administrator
 *
 */
@Component
public class FeignFallbackProvider implements FallbackProvider {

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return headers;
			}

			@Override
			public InputStream getBody() throws IOException {
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> map = new HashMap<>();
				map.put("status", 200);
				map.put("message", "无法连接，请检查您的网络");
				return new ByteArrayInputStream(objectMapper.writeValueAsString(map).getBytes("UTF-8"));
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.OK.getReasonPhrase();
			}

			/**
			 * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的， 不应该把api的404,500等问题抛给客户端
			 * 网关和api服务集群对于客户端来说是黑盒子
			 * 
			 * @return
			 * @throws IOException
			 */
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.OK.value();
			}

			@Override
			public void close() {
			}
		};
	}

	@Override
	public String getRoute() {
		// 如果只返回指定微服务，则:return "cloud-client-feign",return null 表示所有调用都支持回退
		return null;
	}

}
