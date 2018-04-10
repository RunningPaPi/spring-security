package com.imooc.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder defferedResultHolder;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/order")
	public DeferredResult<String> order() throws Exception {
		log.info("主线程开始");
		
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		DeferredResult<String> result = new DeferredResult<>();
		defferedResultHolder.getMap().put(orderNumber, result);
//		Callable<String> result = new Callable<String>() {
//			
//			@Override
//			public String call() throws Exception {
//				log.info("副线程开始");
//				Thread.sleep(1000);
//				log.info("副线程返回");
//				return "success";
//			}
//		};
		log.info("主线程返回");
		return result;
	}
}
