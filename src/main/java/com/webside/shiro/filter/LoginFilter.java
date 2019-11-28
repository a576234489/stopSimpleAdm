package com.webside.shiro.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.webside.plateformaccount.model.UserEntity;
import com.webside.shiro.ShiroAuthenticationManager;


/**
 * 
 * <p>Description: 判断登录</p>
 * <p>Company: 静之殇工作室</p>
 * @author wjggwm
 * @date 2016年7月12日 上午11:34:40
 */
public class LoginFilter  extends AccessControlFilter {
	
	private Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		
		UserEntity user = ShiroAuthenticationManager.getUserEntity();
		logger.info("...LoginFilter...");
		
		if(null != user || isLoginRequest(request, response)){// && isEnabled() ?hi9root necessary?
            return Boolean.TRUE;
        } 
		if (ShiroFilterUtils.isAjax(request)) {// ajax请求
			reloginResponse(response);
			logger.info("xxxx");
		}
		else{
			saveRequestAndRedirectToLogin(request, response);
		}
		return Boolean.FALSE ;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		//保存Request和Response 到登录后的链接
		//saveRequestAndRedirectToLogin(request, response);
		saveRequest(request);
		return Boolean.FALSE ;
	}
	
	//hi9root
	private void reloginResponse(ServletResponse response){
		Map<String, String> map = new HashMap<>();
		map.put("status", "403");
		map.put("message", "会话超时，请重新登录！"); //i18n
//		map.put("message", "\u5F53\u524D\u7528\u6237\u6CA1\u6709\u767B\u5F55\uFF01"); //i18n
		map.put("url", "/login.html");
		try {
			ServletResponse resp = response;
			resp.setCharacterEncoding("UTF-8");
			response.getWriter().write(JSON.toJSONString(map));
		} catch (Exception e) {
			logger.error("reloginResponse-->Error while outputing JSON", e);
		}
	}
}
