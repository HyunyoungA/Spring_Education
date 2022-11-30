package com.kh.spring.member.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.log.Log4jTest;
import com.kh.spring.member.model.exception.MemberException;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.service.MemberServiceImpl;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes("loginUser") //키값에 로그인 유저가 있으면 해당 그 키를 세션에 올려주겠다.
@Controller	// Controller역할을 갖는 객체 생성해주는 어노테이션 = 빈(객체)등록 -->DI라고한다.DI(Dependency Injection) : 의존성 주입
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	//사용할 로그가 작성되어있는 클래스를 넣어주는 것이다

	
//	@RequestMapping(value="login.me", method = RequestMethod.POST)
//	public void login() {
//		System.out.println("로그인 처리");
//	}
	/**파라미터 전송 받기***/
	//1. HttpServletRequest방식 : JSP/Servlet방식
//	@RequestMapping(value="login.me", method = RequestMethod.POST)
//	public void login(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//		
//		System.out.println("id1 :" + id);
//		System.out.println("pwd1 :" + pwd);
//	}
	//2. @RequestParam방식
	//	스프링에서 좀 더 간단하게 파라미터를 받아올 수 있는 방법
	//	HttpServletRequest와 비슷하게 request객체를 이용하여 데이터를 전송받으나 원하는 타입으로 자동 형변환 가능
	//	value			view에서 받아올 파라미터 이름, 어노테이션에 들어가는 속성이 하나 뿐이라면 자동으로 value가 인지하여 생략가능
	//	defaultValue	값이 null이거나 들어오지 않았을 때 기본적으로 들어갈 데이터 지정
	//	required		해당 파라미터가 필수적인 설정, 기본 값 true, 필수값은 아니고 나중에 사용하려고 미리 만든값
//	@RequestMapping(value="login.me", method = RequestMethod.POST)
//	public void login(@RequestParam("id") String id, @RequestParam("pwd") String pwd) {
//	public void login(@RequestParam(value="id") String id, @RequestParam(value="pwd") String pwd) {
//	public void login(@RequestParam(value="id", defaultValue="hello") String id, @RequestParam(value="pwd", defaultValue="world") String pwd) {
//	public void login(@RequestParam(value="id", defaultValue="hello") String id,
//					  @RequestParam(value="pwd", defaultValue="world") String pwd,
//					  @RequestParam(value="test", required=false, defaultValue="spring") String test) {//존재하지않은 값을 반환받으려할 때 400에러가 뜬다. 나중에는 사용할 수도 있다.
//		System.out.println("id2 :" + id);
//		System.out.println("pwd2 :" + pwd);
//		System.out.println("test :" + test);
//	}
	//3. @RequestParam 생략방식
	//	매개변수 명이 파라미터 명과 동일해야 mapping
	//@RequestParam에서 사용할 수 있는 속성 사용 불가
	//이왕이면 쓰는게 좋다. 뷰에서 받아오는건지 지금 매개변수 구분인지 쓰는게 좋다
//	@RequestMapping(value="login.me", method = RequestMethod.POST)
//	public void login(String id, String pwd) {
//		System.out.println("id3 : " + id);
//		System.out.println("pwd3 : " + pwd);
//	}
	
	//4. @ModelAttribute방식
	//		요청 파라미터가 많은 경우 객체 타입으로 넘겨 받음
	//		파라미터명이랑 셋터와 이름이 같아야 매핑이 된다. 이름이 다르면 매핑 안됨. 기본생성자 없으면 안됨!! 기본생성자로 객체를 만들어줘야 셋터도 진행된다.
//	@RequestMapping(value="login.me", method = RequestMethod.POST)
//	public void login(@ModelAttribute Member m) {
//		System.out.println("m : " + m);
//	}
	
	//5.  @ModelAttribute 생략방식-->추천하지않음
//	@RequestMapping(value="login.me", method = RequestMethod.POST)
//	public void login(Member m) {
//		//주도권을 우리가 가지게 된다, 객체주소를 찍어볼때 주소가 다르게 나온다=요청할 때마다 new 새롭게 객체를 만들어내기때문에 주소가 다르게 나오는것이다.
////		MemberService mService = new MemberService();//이름을 바꾸면 변경에대해 직접적인 영향을 받고, 요청을 할때마다 새로운 주소값을 반환한다 ==> 높은 결합도
////		System.out.println(mService);
//		Member loginMember = mService.login(m);
//	}
	
	/**********데이터 전달하기**********/
	//1. Model객체 사용
	//	Servlet에서 사용하던 requestScope와 비슷 = scope는 request
	//	뷰에 전달하고자 하는 데이터를 맵형식(key, value)으로 담을 때 사용
//	@RequestMapping(value="login.me", method = RequestMethod.POST)
//	public String login(Member m, Model model, HttpSession session) {//getSession해서 가져올 필요없다. 우리가 자유롭게 만들어서 사용할 수 있기 때문에 
//		//주도권을 우리가 가지게 된다, 객체주소를 찍어볼때 주소가 다르게 나온다=요청할 때마다 new 새롭게 객체를 만들어내기때문에 주소가 다르게 나오는것이다.
////		MemberService mService = new MemberService();//이름을 바꾸면 변경에대해 직접적인 영향을 받고, 요청을 할때마다 새로운 주소값을 반환한다 ==> 높은 결합도
////		System.out.println(mService);
//		Member loginMember = mService.login(m);
//		//prefix : /WEB-INF/views/member/
//		if(loginMember != null) {
//			session.setAttribute("loginUser", loginMember);
////			return "../home";
//			// /WEB-INF/views/member/../home.jsp
//			//한번 뒤로가서 다시찾기(주소창에 노출되서 redirect:사용)
//			// /WEB-INF/views/home.jsp
//			return "redirect:home.do";
//		}else {
//			model.addAttribute("msg", "로그인에 실패하였습니다.");
//			return "../common/errorPage";
//			
//		}
//	}
	
	//2. ModelAndView객체 사용
		//	Model + View --> 데이터와 뷰를 한 번에 담는 객체
		//반환값도 ModelAndView이다.
		@RequestMapping(value="login.me", method = RequestMethod.POST)
		public ModelAndView login(Member m, ModelAndView mv, HttpSession session) {//getSession해서 가져올 필요없다. 우리가 자유롭게 만들어서 사용할 수 있기 때문에 
			Member loginMember = mService.login(m);
		
			if(loginMember != null) {
				session.setAttribute("loginUser", loginMember);
				mv.setViewName("redirect:home.do");
			}else {
				mv.addObject("msg", "로그인에 실패하였습니다.");
				mv.setViewName("../common/errorPage");
			}
			return mv;
		}
	
//	@RequestMapping("logout.me")
//	public String logout(HttpSession session) {
//		//세션 무효화/ 기본적으로 String으로 진행한다생각하기
//		session.invalidate();
//		return "redirect:home.do";
//	}
	
//	//로그인
//	// + session에 저장할 때 @SessionAttributes사용(이전에는 세션스코프로 했다) @SessionAttributes사용할 때는 Model이 필요하다.
//	//		Model에 attribute가 추가될 때 자동으로 키 값을 찾아서 세션에 등록하는 기능
//	@RequestMapping(value="login.me", method = RequestMethod.POST)
//	public String login(Member m, Model model) {//@SessionAttributes사용할 때는 Model이 필요하다.
//		Member loginMember = mService.login(m);
//	//로그인이 유지되지않는데--> 세션에 올라가있지않아서
//	//세션에 올리고 싶으면 @SessionAttributes 사용->상단에 @SessionAttributes("loginUser") //키값에 로그인 유저가 있으면 해당 그 키를 세션에 올려주겠다.
//	//로그아웃 방법도 다르다!
//		if(loginMember != null) {
//			model.addAttribute("loginUser", loginMember);
//			return "redirect:home.do";
//		}else {
//			model.addAttribute("msg", "로그인에 실패하였습니다.");
//			return "../common/errorPage";
//		}
//	}
	
	//로그아웃
	@RequestMapping("logout.me")
	public String logout(SessionStatus status) {
		//setComplete() :세션 무효화   | 기본적으로 String으로 진행한다생각하기
		status.setComplete();
		return "redirect:home.do";
	}
	
	//회원가입
	@RequestMapping("enrollView.me")
	public String enrollView() {
		if(logger.isDebugEnabled()) {
			logger.debug("회원등록페이지");
		}
		return "memberJoin";
	}
	
	//회원가입url처리
	@RequestMapping("minsert.me")
	public String insertMember(@ModelAttribute Member m, @RequestParam("post") String post, @RequestParam("address1") String address1,
								@RequestParam("address2") String address2) {
//		System.out.println(m);
//		System.out.println(post + "/" + address1 + "/" + address2);
		
		m.setAddress(post + "/" + address1 + "/" + address2);
		
		//BCrypt : 암호화방식-->복호화안됨.
		//원본 데이터 --> salting, salt값 --> 암호화 데이터
		//				random생성
		//라이브러리+객체 추가해야한다.
//		System.out.println(bcrypt);
		//    m.getPwd(): 원본 비밀번호를 넘기는 것이다.
		String encPwd = bcrypt.encode(m.getPwd());
//		System.out.println(encPwd);
		
		m.setPwd(encPwd);
		
//		System.out.println(m);
		//인터페이스에 생성이 된다.
		int result = mService.insertMember(m);
		
		if(result > 0) {
			return "redirect:home.do";
		} else {
			throw new MemberException("회원가입에 실패하였습니다.");
		}
		
	}
	
	//로그인+암호화해도 되는
	@RequestMapping(value="login.me", method = RequestMethod.POST)
	public String login(Member m, Model model) {
		System.out.println(bcrypt.encode(m.getPwd()));
		//loginMember: 아이디 일치하는 회원의 정보가 들어가 있을 것이다. member-mapper에서 아이디만 비교하게했기때문에
		Member loginMember = mService.login(m);
		//matches: 암호화된 비번과 암호화된 비번을 비교해서 일치하면 true, 일치하지않을때 false 반환 
		//rawPassword: 원본형태 비번=m.getPwd(), encodedPassword: 비교할 인코딩 비번=loginMember.getPwd()
		boolean match = bcrypt.matches(m.getPwd(), loginMember.getPwd());
		if(match) {
			model.addAttribute("loginUser", loginMember);
			logger.info(loginMember.getId());
			return "redirect:home.do";
		}else {
//			model.addAttribute("msg", "로그인에 실패하였습니다.");
//			return "../common/errorPage";
			throw new MemberException("로그인에 실패하였습니다.");
		}
	}
	//내정보 수정
	@RequestMapping("myinfo.me")
	public String myInfo() {
		return "mypage";
	}
	
	@RequestMapping("mupdateView.me")
	public String updateView() {
		return "memberUpdateForm";
	}
	
	@RequestMapping("mupdate.me")
	public String updateMember(@ModelAttribute Member m,
								@RequestParam("post") String post,
								@RequestParam("address1") String address1,
								@RequestParam("address2") String address2,
								Model model) {
		
		m.setAddress(post + "/" + address1 + "/" + address2);
		System.out.println(post + "/" + address1 + "/" + address2);
		
		
		int result = mService.updateMember(m);
		//세션을 보고있기때문에 세션도 같이 업데이트해줘야한다
		//세션을 바꿔주냐 안바꿔주냐의 차이!!!Model model추가!
		//Member loginUser = mService.login(m);추가!
		//model.addAttribute("loginUser", loginUser);추가!
		Member loginUser = mService.login(m);
		if(result > 0) {
			model.addAttribute("loginUser", loginUser);
			return "redirect:myinfo.me";
		} else {
			throw new MemberException("회원정보 수정에 실패하였습니다.");
		}
	}
	
	//비밀번호 수정
	@RequestMapping("mpwdUpdateView.me")
	public String pwdUpdateView() {
		return "memberPwdUpdateForm";
	}
	
	@RequestMapping("mPwdUpdate.me")
	public String memberPwdUpdate(@RequestParam("pwd") String oldPwd,
									@RequestParam("newPwd1") String newPwd1,
									Model model) {
		//Model model에 loginUser를 넣어놨기 때문에 가져올 수 있다
		Member m = (Member)model.getAttribute("loginUser");//로그인한 사람의 정보
		//암호화를 했기때문에 암호화가 된 비밀번호와 현재 비밀번호를 먼저 비교해준다
		
		
		int result = 0;
		String encode = null;
		if(bcrypt.matches(oldPwd, m.getPwd())) {//oldPwd: 내가 입력한 비밀번호 m.getPwd(): 내가 회원가입한 비밀번호 두개가 같으면(matches)
			HashMap<String, String> map = new HashMap<>();
			map.put("id", m.getId());
			encode = bcrypt.encode(newPwd1);//암호화가 되어있는 비밀번호
			map.put("newPwd1", encode);//암호화가 되어있는 비밀번호
			
			result = mService.updatePwd(map);
		}
		
		if(result > 0) {
			m.setPwd(encode);
			model.addAttribute("loginUser", m);
			return "redirect:myinfo.me";
		} else {
			throw new MemberException("비밀번호 수정에 실패하였습니다.");
		}
	}
	
	//회원탈퇴
	@RequestMapping("mdelete.me")
	public String memberdelete(Model model) {
		String id =((Member)model.getAttribute("loginUser")).getId();
		int result = mService.deleteMember(id);
		
		if(result > 0) {
			return "redirect:logout.me";
		} else {
			throw new MemberException("회원탈퇴에 실패하였습니다.");
		}
		
	}
	//아이디 중복 ajax
	@RequestMapping("dupId.me")
	public void duplicateId(@RequestParam("id") String id, HttpServletResponse response) {
		System.out.println(id);
		
		int result = mService.checkIdDup(id);
		System.out.println(result);
		
		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
