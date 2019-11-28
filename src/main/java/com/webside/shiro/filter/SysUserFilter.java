package com.webside.shiro.filter;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.webside.plateformaccount.mapper.PlateformAccountMapper;
/**
 * 
 * @author wjggwm
 *
 */
public class SysUserFilter extends PathMatchingFilter {

	@Inject
	private PlateformAccountMapper plateformAccountMapper;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String userName = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("user", plateformAccountMapper.findByName(userName));
        return true;
    }
}