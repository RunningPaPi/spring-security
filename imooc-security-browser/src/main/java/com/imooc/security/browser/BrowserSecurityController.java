package com.imooc.security.browser;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrowserSecurityController {

	private Logger log = LoggerFactory.getLogger(getClass());

	private RequestCache requestCache = new HttpSessionRequestCache();

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@RequestMapping()
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public String requiredAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest != null) {
			String targeturl = savedRequest.getRedirectUrl();
			log.info("引发跳转的请求是：{}", targeturl);
			if (StringUtils.endsWith(targeturl, ".html")) {
				redirectStrategy.sendRedirect(request, response, "");
			}
		}

		return null;
	}
}
