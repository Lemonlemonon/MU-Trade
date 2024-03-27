package com.fyp.mutrade.controller.common;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fyp.mutrade.util.CpachaUtil;

/**
 * System verification code generation controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/cpacha")
public class CpachaController {

	private Logger log = LoggerFactory.getLogger(CpachaController.class);
	
	/**
	 * Verification code generator
	 * @param vcodeLength
	 * @param fontSize
	 * @param width
	 * @param height
	 * @param method
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/generate_cpacha",method=RequestMethod.GET)
	public void generateCpacha(
			@RequestParam(name="vl",defaultValue="4")Integer vcodeLength,//vcodeLength
			@RequestParam(name="fs",defaultValue="21")Integer fontSize,//fontSize,
			@RequestParam(name="w",defaultValue="98")Integer width,//width
			@RequestParam(name="h",defaultValue="33")Integer height,//height
			@RequestParam(name="method",defaultValue="admin_login")String method,//Use the name of the method being called as the key to store it in the session
			HttpServletRequest request,
			HttpServletResponse response){
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLength,fontSize,width,height);
		String generatorVCode = cpachaUtil.generatorVCode();
		//Place the generated verification code into the session for later use
		request.getSession().setAttribute(method, generatorVCode);
		log.info("verification code generated!，method=" + method + ",value=" + generatorVCode);
		try {
			ImageIO.write(cpachaUtil.generatorRotateVCodeImage(generatorVCode, true), "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
