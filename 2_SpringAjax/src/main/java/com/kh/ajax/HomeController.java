package com.kh.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("test1.do")
	public void test1(@RequestParam("name") String name, @RequestParam("age") int age, HttpServletResponse response) {
		System.out.println(name);
		System.out.println(age);
		//aJax 반환값return이 필요없다 한 화면에서 움직이기 때문에 return을 지워주고->String을 void로 변경해준다
		//들어온 데이터가 강건강, 20이 맞으면 뷰에 ok 전송/ 맞지않으면 fail 전송
		try {
			PrintWriter out = response.getWriter();
			
			if(name.equals("강건강") && age == 20) {
				out.print("ok");
			} else {
				out.print("fail");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="test2.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String test2(/*HttpServletResponse response*/) {
		//비동기방식 : void
		JSONObject job = new JSONObject();
		job.put("no", 123);
		job.put("title", "return json object test");
		job.put("writer", "남나눔");
		job.put("content", "JSON객체를 뷰로 리턴합니다.");
		//dataType이 잘 먹지않는 경우가 있다.
		//@RequestMapping(value="test2.do")값이 한개면 value를 안넣어도된다.
		
		
//		JSONObject job = new JSONObject();
//		job.put("no", 123);
//		job.put("title", "return json object test");
//		try {
//			job.put("writer", URLEncoder.encode("남나눔", "UTF-8"));
//			job.put("content", URLEncoder.encode("JSON객체를 뷰로 리턴합니다.", "UTF-8"));
//		} catch (JSONException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
		//URLEncoder.encode : 스트링 부분만 넣어서 진행하준다.
		
//		try {
//			response.setContentType("application/json; charset=UTF-8");//문자열의 인코딩
//			response.getWriter().print(job);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//toString으로 반환하면 뷰리졸버가 반환하는 .jsp가 붙어서
		//스트링 반환하면 뷰리졸버가 경로를 붙혀서 해당경로를 찾는다
		//뷰가 아니라 내가 보낼 데이터라는 것을 알려줘야한다.
		//리퀘스트맵핑아래에 @ResponseBody 어노테이션 붙힌다.
		//@ResponseBody : 리스펀스 객체 바디부분에 해당 데이터를 보내라!-->뷰리졸버가 아니라 리스펀스객체에 데이터를 담아서 보내!
		
		//인코딩 home.jsp에서 데이터타입 제이슨으로 지정
		
		//원래는 toJSONString();-
		//제이슨 Array도 가능하다
		System.out.println(job);
		return job.toString();
		
		
	}
	
	
	
	
	
	
}
