/**
 * 
 */
package com.imooc.security.core.validate.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imooc.security.core.properties.SecurityProperties;

/**
 * @author Caesar
 *
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
	private AuthenticationFailureHandler authenticationFailureHandler;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	private Set<String> urls = new HashSet<>();

	private SecurityProperties securityProperties;

	private AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String[] configUrls = StringUtils.split(securityProperties.getCode().getImage().getUrl(), ",");
		
		if(ArrayUtils.isNotEmpty(configUrls)){
			for (String configUrl : configUrls) {
				urls.add(configUrl);
			}
		}
		urls.add("/authentication/form");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean action = false;
		for (String url : urls) {
			if (pathMatcher.match(url, request.getRequestURI())) {
				action = true;
			}
		}
		if (action) {
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码不能为空");
		}

		if (codeInSession == null) {
			throw new ValidateCodeException("验证码不存在");
		}

		if (codeInSession.isExpired()) {
			sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
			throw new ValidateCodeException("验证码已过期");
		}

		if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException("验证码不匹配");
		}

		sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

}
