package com.webside.index.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.webside.base.basecontroller.BaseController;
import com.webside.exception.SystemException;
import com.webside.ip2region.DbSearcher;
import com.webside.logininfo.model.LoginInfoEntity;
import com.webside.logininfo.service.LoginInfoService;
import com.webside.plateformaccount.model.UserEntity;
import com.webside.plateformaccount.service.PlateformAccountService;
import com.webside.resource.model.ResourceEntity;
import com.webside.resource.service.ResourceService;
import com.webside.role.model.RoleEntity;
import com.webside.role.service.RoleService;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.util.IpUtil;
import com.webside.util.JsonResult;
import com.webside.util.TreeUtil;
import com.webside.util.crypto.EndecryptUtils;

/**
 * 
 * @ClassName: IndexController
 * @Description: 登录、注册、退出、验证码
 * @author gaogang
 * @date 2016年7月12日 下午3:20:47
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class IndexController extends BaseController {

	@Autowired
	private PlateformAccountService plateformAccountService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private LoginInfoService loginInfoService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private Producer captchaProducer;

	@Autowired
	private DbSearcher ipSearcher;

//	@RequestMapping(value = "login.html", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
//	public String login(Model model, HttpServletRequest request) {
//		try{
//			request.removeAttribute("error");
//			return "/login";
//		}catch(Exception e)
//		{
//			throw new SystemException(e);
//		}
//	}
	@RequestMapping(value ="/shiroGet2")
	@ResponseBody
	public String shiroGet() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		String result = (String) session.getAttribute("aa"); // 获取session中的验证码
		String expected = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY); // 获取session中的验证码
		System.out.println(result);
		System.out.println(expected);
		return result+expected;
	}
	/**
	 * 用户登录 认证过程： 1、想要得到Subject对象,访问地址必须在shiro的拦截地址内,不然会报空指针
	 * 2、用户输入的账号和密码,存到UsernamePasswordToken对象中,然后由shiro内部认证对比
	 * 3、认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理 4、当以上认证成功后会向下执行,认证失败会抛出异常
	 * 
	 * @param accountName 账户名称
	 * @param password    密码
	 * @return
	 */
	@RequestMapping(value = "login_login", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	@ResponseBody
	public JsonResult userLogin(UserEntity userEntity, String captcha, Boolean rememberMe, HttpServletRequest request) {
		UsernamePasswordToken token = null;
		String url = "";
		try {
			//String expected = ShiroAuthenticationManager.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String expected = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY); // 获取session中的验证码
			String a = (String) session.getAttribute("aa"); // 获取session中的验证码
			System.out.println(expected+"11");
			System.out.println(expected+a);
			// check captcha
			if (!StringUtils.equalsIgnoreCase(expected, captcha)) {
				request.setAttribute("accountName", userEntity.getAccountName());
				request.setAttribute("password", userEntity.getPassword());
				return new JsonResult("/login.html");
			} else {
				// 想要得到Subject对象,访问地址必须在shiro的拦截地址内,不然会报空指针
				Subject subject = SecurityUtils.getSubject();
				token = new UsernamePasswordToken(userEntity.getAccountName(), userEntity.getPassword());
				// 记住用户登录状态
				token.setRememberMe(rememberMe);
				subject.login(token);
				if (subject.isAuthenticated()) {
					userEntity = (UserEntity) subject.getPrincipal();
					LoginInfoEntity loginInfo = new LoginInfoEntity();
					loginInfo.setUserId(userEntity.getId().intValue());
					loginInfo.setAccountName(userEntity.getAccountName());
					// String ip = SecurityUtils.getSubject().getSession().getHost();
					String ip = IpUtil.getIpAddr(request);
					String region = ipSearcher.memorySearch(ip).getRegion();
					String[] regions = StringUtils.split(region, '|');
					loginInfo.setLoginIp(ip);
					loginInfo.setProvince(regions[2]);
					loginInfo.setCity(regions[3]);
					loginInfo.setRegion(region);
					loginInfoService.log(loginInfo);
					request.removeAttribute("error");
					/**
					 * shiro 获取登录之前的地址
					 */
					SavedRequest savedRequest = WebUtils.getSavedRequest(request);
					String contextPath = request.getContextPath();
					if (null != savedRequest) {
						url = savedRequest.getRequestUrl().replace(contextPath, "");
					}
					// 如果登录之前没有地址，那么就跳转到首页。
					if (StringUtils.isBlank(url)) {
						url = "/view/index.html";
					}
					// 由于系统使用ajax请求，所以均跳转到主页
					url = "/view/index.html";
				} else {
					logger.info("161");
					token.clear();
					request.setAttribute("accountName", userEntity.getAccountName());
					request.setAttribute("password", userEntity.getPassword());
					return  new JsonResult("/login.html");
				}
			}
		} catch (UnknownAccountException uae) {
			return new JsonResult("/login.html");
		} catch (IncorrectCredentialsException ice) {
			return new JsonResult("/login.html");
		} catch (LockedAccountException e) {
			return new JsonResult("/login.html");
		} catch (ExcessiveAttemptsException e) {
			return new JsonResult("/login.html");
		} catch (ExpiredCredentialsException eca) {
			return new JsonResult("/login.html");
		} catch (AuthenticationException e) {
			return new JsonResult("/login.html");
		} catch (Exception e) {
			logger.info("186", e);
			return new JsonResult("/login.html");
		} finally {

			if (null != token) {
				token.clear();
			}
			request.setAttribute("accountName", userEntity.getAccountName());
			request.setAttribute("password", userEntity.getPassword());
		}
		return new JsonResult(url);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "index/data", method = RequestMethod.GET)
	@ResponseBody
	public Object indexData() {
		try {
			UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
			List<ResourceEntity> list = resourceService.findResourcesMenuByUserId(userEntity.getId().intValue());
			List<ResourceEntity> treeList = TreeUtil.getChildResourceEntitys(list, null);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("list", treeList);
			map.put("userEntity", userEntity);
			return new JsonResult(map);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * 用户注册
	 * 
	 * @param userEntity
	 * @return
	 */
	@RequestMapping(value = "register.html", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String register(UserEntity userEntity) {
		try {
			String password = userEntity.getPassword();
			// 加密用户输入的密码，得到密码和加密盐，保存到数据库
			UserEntity user = EndecryptUtils.md5Password(userEntity.getAccountName(), userEntity.getPassword(), 2);
			// 设置添加用户的密码和加密盐
			userEntity.setPassword(user.getPassword());
			userEntity.setCredentialsSalt(user.getCredentialsSalt());
			// 设置创建者姓名
			userEntity.setCreatorName(userEntity.getUserName());
			userEntity.setCreateTime(new Date(System.currentTimeMillis()));
			// 设置锁定状态：未锁定；删除状态：未删除
			userEntity.setLocked(0);
			userEntity.setDeleteStatus(0);
			// 通过注册页面注册的用户统一设置为普通用户
			RoleEntity roleEntity = roleService.findByName("普通用户");
			userEntity.setRole(roleEntity);
			// 保存用户注册信息
			plateformAccountService.add(userEntity);
			return "login";
		} catch (Exception e) {
			throw new SystemException(e);
		}

	}

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "logout.html", method = RequestMethod.GET)
	public String logout() {
		// 这里执行退出系统之前需要清理数据的操作

		// 注销登录
		ShiroAuthenticationManager.logout();
		return "redirect:/";
	}

	@RequestMapping(value = "captcha.html", method = RequestMethod.GET)
	public void kaptcha(HttpServletRequest req, HttpServletResponse rsp) {
		ServletOutputStream out = null;
		try {
			rsp.setDateHeader("Expires", 0);
			rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
			rsp.setHeader("Pragma", "no-cache");
			rsp.setContentType("image/jpeg");
				
			String capText = captchaProducer.createText();
			// 将验证码存入shiro 登录用户的session
			//ShiroAuthenticationManager.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
			BufferedImage image = captchaProducer.createImage(capText);
			out = rsp.getOutputStream();
			ImageIO.write(image, "jpg", out);
			out.flush();
		} catch (IOException e) {
			throw new SystemException(e);
		} finally {
			try {
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				logger.error("关闭输出流异常:", e);
			}
		}
	}

}
