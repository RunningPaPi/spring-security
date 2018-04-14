package com.imooc.security.core.validate.code.image;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.ImageCode;
import com.imooc.security.core.validate.code.ipml.AbstractValidateCodeProcessor;
/**
 * 图片验证码处理器
 * @author Caesar
 *
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode>{

	/**
	 * 发送图形验证码，将其写到相应中
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		ImageIO.write(imageCode.getImage(), "PNG", request.getResponse().getOutputStream());
	}

}
