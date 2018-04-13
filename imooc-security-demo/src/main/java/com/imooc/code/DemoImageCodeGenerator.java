package com.imooc.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.ImageCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
/**
 * 高级开发人员必须掌握 的技巧
 * 以增量的方式去适应变化
 */
	@Override
	public ImageCode createImageCode(ServletWebRequest request) {
		System.out.println("更高级的图形验证码生成逻辑");
		return null;
	}

}
