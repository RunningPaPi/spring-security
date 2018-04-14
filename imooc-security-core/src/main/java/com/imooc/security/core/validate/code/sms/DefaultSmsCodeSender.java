package com.imooc.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSmsCodeSender implements SmsCodeSender{

	Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void send(String mobile, String code) {
		log.info("向手机{}发送短信验证码：{}", mobile, code);
	}

}
