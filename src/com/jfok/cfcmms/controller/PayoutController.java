package com.jfok.cfcmms.controller;


import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.service.PayoutService;
import com.jfok.cfcmms.share.TreeNodeRecord;

@Controller
@RequestMapping("/modulepayout")
public class PayoutController {

	@Resource
	private PayoutService payoutService;

	/**
	 * 生成系统首页的审核模块的全局概览， 每个模块－－ 有 m 条待支付，我可以支付 n 条
	 * 
	 * @param request
	 */

	@RequestMapping("/getmodulepayoutinfo.do")
	public @ResponseBody
	List<TreeNodeRecord> genAllModuleAuditingInfo(HttpServletRequest request) {
		return payoutService.genAllModulePayoutInfo(request);
	}

}
